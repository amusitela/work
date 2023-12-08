package com.cumt.bankapp.mapper;

import java.util.List;
import com.cumt.bankapp.domain.Flow;
import org.springframework.stereotype.Repository;

/**
 * flowMapper接口
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Repository
public interface FlowMapper 
{
    /**
     * 查询flow
     * 
     * @param transactionId flow主键
     * @return flow
     */
    public Flow selectFlowByTransactionId(String transactionId);

    /**
     * 查询flow列表
     * 
     * @param flow flow
     * @return flow集合
     */
    public List<Flow> selectFlowList(Flow flow);

    /**
     * 新增flow
     * 
     * @param flow flow
     * @return 结果
     */
    public int insertFlow(Flow flow);

    /**
     * 修改flow
     * 
     * @param flow flow
     * @return 结果
     */
    public int updateFlow(Flow flow);

    /**
     * 删除flow
     * 
     * @param transactionId flow主键
     * @return 结果
     */
    public int deleteFlowByTransactionId(String transactionId);

    /**
     * 批量删除flow
     * 
     * @param transactionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFlowByTransactionIds(String[] transactionIds);
}
