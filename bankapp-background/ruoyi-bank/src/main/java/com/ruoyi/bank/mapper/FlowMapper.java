package com.ruoyi.bank.mapper;

import java.util.List;
import com.ruoyi.bank.domain.Flow;

/**
 * FlowMapper接口
 * 
 * @author cbk
 * @date 2023-12-15
 */
public interface FlowMapper 
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
     * 删除Flow
     * 
     * @param transactionId Flow主键
     * @return 结果
     */
    public int deleteFlowByTransactionId(String transactionId);

    /**
     * 批量删除Flow
     * 
     * @param transactionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFlowByTransactionIds(String[] transactionIds);
}
