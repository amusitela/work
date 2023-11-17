package com.cumt.bankapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cumt.bankapp.mapper.FlowMapper;
import com.cumt.bankapp.domain.Flow;
import com.cumt.bankapp.service.IFlowService;
import com.ruoyi.common.core.text.Convert;

/**
 * flowService业务层处理
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Service
public class FlowServiceImpl implements IFlowService 
{
    @Autowired
    private FlowMapper flowMapper;

    /**
     * 查询flow
     * 
     * @param transactionId flow主键
     * @return flow
     */
    @Override
    public Flow selectFlowByTransactionId(String transactionId)
    {
        return flowMapper.selectFlowByTransactionId(transactionId);
    }

    /**
     * 查询flow列表
     * 
     * @param flow flow
     * @return flow
     */
    @Override
    public List<Flow> selectFlowList(Flow flow)
    {
        return flowMapper.selectFlowList(flow);
    }

    /**
     * 新增flow
     * 
     * @param flow flow
     * @return 结果
     */
    @Override
    public int insertFlow(Flow flow)
    {
        return flowMapper.insertFlow(flow);
    }

    /**
     * 修改flow
     * 
     * @param flow flow
     * @return 结果
     */
    @Override
    public int updateFlow(Flow flow)
    {
        return flowMapper.updateFlow(flow);
    }

    /**
     * 批量删除flow
     * 
     * @param transactionIds 需要删除的flow主键
     * @return 结果
     */
    @Override
    public int deleteFlowByTransactionIds(String transactionIds)
    {
        return flowMapper.deleteFlowByTransactionIds(Convert.toStrArray(transactionIds));
    }

    /**
     * 删除flow信息
     * 
     * @param transactionId flow主键
     * @return 结果
     */
    @Override
    public int deleteFlowByTransactionId(String transactionId)
    {
        return flowMapper.deleteFlowByTransactionId(transactionId);
    }
}
