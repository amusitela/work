package com.ruoyi.bank.service;

import java.util.List;
import com.ruoyi.bank.domain.Flow;

/**
 * FlowService接口
 * 
 * @author cbk
 * @date 2023-12-15
 */
public interface IFlowService 
{
    /**
     * 查询Flow
     * 
     * @param transactionId Flow主键
     * @return Flow
     */
    public Flow selectFlowByTransactionId(String transactionId);

    /**
     * 查询Flow列表
     * 
     * @param flow Flow
     * @return Flow集合
     */
    public List<Flow> selectFlowList(Flow flow);

    /**
     * 新增Flow
     * 
     * @param flow Flow
     * @return 结果
     */
    public int insertFlow(Flow flow);

    /**
     * 修改Flow
     * 
     * @param flow Flow
     * @return 结果
     */
    public int updateFlow(Flow flow);

    /**
     * 批量删除Flow
     * 
     * @param transactionIds 需要删除的Flow主键集合
     * @return 结果
     */
    public int deleteFlowByTransactionIds(String[] transactionIds);

    /**
     * 删除Flow信息
     * 
     * @param transactionId Flow主键
     * @return 结果
     */
    public int deleteFlowByTransactionId(String transactionId);
}
