package com.ruoyi.bank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bank.mapper.FlowMapper;
import com.ruoyi.bank.domain.Flow;
import com.ruoyi.bank.service.IFlowService;

/**
 * FlowService业务层处理
 * 
 * @author cbk
 * @date 2023-12-15
 */
@Service
public class FlowServiceImpl implements IFlowService 
{
    @Autowired
    private FlowMapper flowMapper;

    /**
     * 查询Flow
     * 
     * @param transactionId Flow主键
     * @return Flow
     */
    @Override
    public Flow selectFlowByTransactionId(String transactionId)
    {
        return flowMapper.selectFlowByTransactionId(transactionId);
    }

    /**
     * 查询Flow列表
     * 
     * @param flow Flow
     * @return Flow
     */
    @Override
    public List<Flow> selectFlowList(Flow flow)
    {
        return flowMapper.selectFlowList(flow);
    }

    /**
     * 新增Flow
     * 
     * @param flow Flow
     * @return 结果
     */
    @Override
    public int insertFlow(Flow flow)
    {
        return flowMapper.insertFlow(flow);
    }

    /**
     * 修改Flow
     * 
     * @param flow Flow
     * @return 结果
     */
    @Override
    public int updateFlow(Flow flow)
    {
        return flowMapper.updateFlow(flow);
    }

    /**
     * 批量删除Flow
     * 
     * @param transactionIds 需要删除的Flow主键
     * @return 结果
     */
    @Override
    public int deleteFlowByTransactionIds(String[] transactionIds)
    {
        return flowMapper.deleteFlowByTransactionIds(transactionIds);
    }

    /**
     * 删除Flow信息
     * 
     * @param transactionId Flow主键
     * @return 结果
     */
    @Override
    public int deleteFlowByTransactionId(String transactionId)
    {
        return flowMapper.deleteFlowByTransactionId(transactionId);
    }
}
