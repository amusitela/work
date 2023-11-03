package com.ruoyi.web.controller.activiti;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Api(value = "动态流程接口")
@Controller
@RequestMapping("/dynamic/flow")
public class DynamicFlowController {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    RepositoryService repositoryService;

    @Resource
    private HistoryService historyService;



    @ApiOperation("遍历流程信息")
    @GetMapping(value = "/info/{processInstanceId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable String processInstanceId) {
        String processDefinitionId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof UserTask) {
                UserTask userTask = (UserTask) flowElement;
                System.out.println(flowElement.getName());
                System.out.println(flowElement.getId());
                System.out.println(userTask.getAssignee());
                String assigneeEl = userTask.getAssignee();
                if (StringUtils.isBlank(assigneeEl)) {
                    continue;
                }
                if (assigneeEl.startsWith("${") && assigneeEl.endsWith("}") && assigneeEl.length() > 3) {
                    String assignee = assigneeEl.substring(2, assigneeEl.length() - 2);
                    System.out.println("assignee:" + assignee);
                }
            }
        }
        return AjaxResult.success(flowElements);
    }

    @ApiOperation("撤销:强制结束一个流程")
    @GetMapping(value = "/forceEnd/{taskId}")
    @ResponseBody
    public AjaxResult forceEnd(@PathVariable String taskId) {
        Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId = runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult().getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        // 寻找流程实例当前任务的activeId
        Execution execution = runtimeService.createExecutionQuery().executionId(t.getExecutionId()).singleResult();
        String activityId = execution.getActivityId();
        FlowNode currentNode = (FlowNode)bpmnModel.getMainProcess().getFlowElement(activityId);
        // 创建结束节点和连接线
        EndEvent end = new EndEvent();
        end.setName("强制结束");
        end.setId("forceEnd");
        List<SequenceFlow> newSequenceFlowList = new ArrayList<SequenceFlow>();
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newFlow");
        newSequenceFlow.setSourceFlowElement(currentNode);
        newSequenceFlow.setTargetFlowElement(end);
        newSequenceFlowList.add(newSequenceFlow);
        // 备份原有方向
        List<SequenceFlow> dataflows = currentNode.getOutgoingFlows();
        List<SequenceFlow> oriSequenceFlows = new ArrayList<SequenceFlow>();
        oriSequenceFlows.addAll(dataflows);
        // 清空原有方向
        currentNode.getOutgoingFlows().clear();
        // 设置新方向
        currentNode.setOutgoingFlows(newSequenceFlowList);
        // 完成当前任务
        taskService.addComment(taskId, t.getProcessInstanceId(), "comment", "撤销流程");
        taskService.complete(taskId);
        // 恢复原有方向
        currentNode.setOutgoingFlows(oriSequenceFlows);
        return AjaxResult.success();
    }

    @ApiOperation("驳回或跳转到指定节点")
    @GetMapping(value = "/jump/{taskId}/{sid}")
    @ResponseBody
    public AjaxResult jump(@PathVariable String taskId, @PathVariable String sid) {
        Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId = runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult().getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        // 寻找流程实例当前任务的activeId
        Execution execution = runtimeService.createExecutionQuery().executionId(t.getExecutionId()).singleResult();
        String activityId = execution.getActivityId();
        FlowNode currentNode = (FlowNode)bpmnModel.getMainProcess().getFlowElement(activityId);
        FlowNode targetNode = (FlowNode)bpmnModel.getMainProcess().getFlowElement(sid);
        // 创建连接线
        List<SequenceFlow> newSequenceFlowList = new ArrayList<SequenceFlow>();
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newFlow");
        newSequenceFlow.setSourceFlowElement(currentNode);
        newSequenceFlow.setTargetFlowElement(targetNode);
        newSequenceFlowList.add(newSequenceFlow);
        // 备份原有方向
        List<SequenceFlow> dataflows = currentNode.getOutgoingFlows();
        List<SequenceFlow> oriSequenceFlows = new ArrayList<SequenceFlow>();
        oriSequenceFlows.addAll(dataflows);
        // 清空原有方向
        currentNode.getOutgoingFlows().clear();
        // 设置新方向
        currentNode.setOutgoingFlows(newSequenceFlowList);
        // 完成当前任务
        taskService.addComment(taskId, t.getProcessInstanceId(), "comment", "跳转节点");
        taskService.complete(taskId);
        // 恢复原有方向
        currentNode.setOutgoingFlows(oriSequenceFlows);
        return AjaxResult.success();
    }

    @ApiOperation("动态创建流程")
    @GetMapping(value = "/createProcess")
    @ResponseBody
    public AjaxResult createProcess() {
        // 开始节点的属性
        StartEvent startEvent=new StartEvent();
        startEvent.setId("start");
        startEvent.setName("start");
        // 普通UserTask节点
        UserTask userTask=new UserTask();
        userTask.setId("userTask");
        userTask.setName("审批任务");
        // 结束节点属性
        EndEvent endEvent=new EndEvent();
        endEvent.setId("end");
        endEvent.setName("end");
        // 连线信息
        List<SequenceFlow> flows=new ArrayList<SequenceFlow>();
        List<SequenceFlow> toEnd=new ArrayList<SequenceFlow>();
        SequenceFlow s1=new SequenceFlow();
        s1.setId("flow1");
        s1.setName("flow1");
        s1.setSourceRef(startEvent.getId());
        s1.setTargetRef(userTask.getId());
        flows.add(s1);

        SequenceFlow s2=new SequenceFlow();
        s2.setId("flow2");
        s2.setName("flow2");
        s2.setSourceRef(userTask.getId());
        s2.setTargetRef(endEvent.getId());
        toEnd.add(s2);
        startEvent.setOutgoingFlows(flows);
        userTask.setOutgoingFlows(toEnd);

        // 给流程对象添加元素
        Process process=new Process();
        process.setId("dynamicProcess");
        process.setName("动态流程");
        process.addFlowElement(startEvent);
        process.addFlowElement(s1);
        process.addFlowElement(userTask);
        process.addFlowElement(s2);
        process.addFlowElement(endEvent);
        // 创建模型对象
        BpmnModel bpmnModel=new BpmnModel();
        bpmnModel.addProcess(process);
        // 流程图自动布局
        new BpmnAutoLayout(bpmnModel).execute();

        // 模型合法性校验
        List<ValidationError> validationErrorList = repositoryService.validateProcess(bpmnModel);
        if (validationErrorList.size() == 0) {
            // 模型合法就部署流程
            Deployment deploy = repositoryService.createDeployment().category("dynamic")
                    .key("dynamicProcess")
                    .addBpmnModel("dynamicProcess.bpmn20.xml", bpmnModel)
                    .deploy();
            return AjaxResult.success("success");
        } else {
            return AjaxResult.error("fail");
        }
    }

}
