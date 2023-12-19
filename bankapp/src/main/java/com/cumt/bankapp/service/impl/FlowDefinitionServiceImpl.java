package com.cumt.bankapp.service.impl;

import com.cumt.common.MyResult;
import com.cumt.common.constant.ProcessConstants;
import com.cumt.common.FlowComment;
import com.cumt.bankapp.service.IFlowDefinitionService;

import com.cumt.bankapp.tools.FlowServiceFactory;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 流程定义
 *
 * @author Tony
 * @date 2021-04-03
 */
@Service
@Slf4j
public class FlowDefinitionServiceImpl extends FlowServiceFactory implements IFlowDefinitionService {


    /**
     * 根据流程定义ID启动流程实例
     *
     * @param procDefId 流程模板ID
     * @param variables 流程变量
     * @return
     */
    @Override
    public MyResult startProcessInstanceById(String procDefId, Map<String, Object> variables) {
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId)
                    .latestVersion().singleResult();
            if (Objects.nonNull(processDefinition) && processDefinition.isSuspended()) {
                return MyResult.error("流程已被挂起,请先激活流程");
            }
//            // 设置流程发起人Id到流程中
//            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
//            identityService.setAuthenticatedUserId(sysUser.getUserId().toString());
//            variables.put(ProcessConstants.PROCESS_INITIATOR, sysUser.getUserId());
//            runtimeService.startProcessInstanceById(procDefId, variables);
//             流程发起时 跳过发起人节点
//            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
//            identityService.setAuthenticatedUserId(sysUser.getUserId().toString());
            variables.put(ProcessConstants.PROCESS_INITIATOR, 1);
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(procDefId, variables);
            // 给第一步申请人节点设置任务执行人和意见
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
            if (Objects.nonNull(task)) {
                taskService.addComment(task.getId(), processInstance.getProcessInstanceId(), FlowComment.NORMAL.getType(), "个体用户:"+ "发起流程申请");
//                taskService.setAssignee(task.getId(), sysUser.getUserId().toString());
                taskService.complete(task.getId(), variables);
            }
            return MyResult.success("流程启动成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("流程启动错误");
        }
    }




}
