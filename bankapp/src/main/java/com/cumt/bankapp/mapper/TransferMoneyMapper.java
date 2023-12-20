package com.cumt.bankapp.mapper;

import java.time.LocalDateTime;
import java.util.List;
import com.cumt.bankapp.domain.TransferMoney;
import org.springframework.stereotype.Repository;

/**
 * transfer_moneyMapper接口
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Repository
public interface TransferMoneyMapper 
{
    /**
     * 查询transfer_money
     * 
     * @param transferId transfer_money主键
     * @return transfer_money
     */
    public TransferMoney selectTransferMoneyByTransferId(String transferId);

    /**
     * 查询transfer_money列表
     * 
     * @param transferMoney transfer_money
     * @return transfer_money集合
     */
    public List<TransferMoney> selectTransferMoneyList(TransferMoney transferMoney);

    /**
     * 新增transfer_money
     * 
     * @param transferMoney transfer_money
     * @return 结果
     */
    public int insertTransferMoney(TransferMoney transferMoney);

    /**
     * 修改transfer_money
     * 
     * @param transferMoney transfer_money
     * @return 结果
     */
    public int updateTransferMoney(TransferMoney transferMoney);

    /**
     * 删除transfer_money
     * 
     * @param transferId transfer_money主键
     * @return 结果
     */
    public int deleteTransferMoneyByTransferId(String transferId);

    /**
     * 批量删除transfer_money
     * 
     * @param transferIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTransferMoneyByTransferIds(String[] transferIds);

    /**
     * 查询transfer_money信息
     *
     * @param transferId transfer_money主键
     * @return 结果
     */
    public List<TransferMoney> selectAllFlow(String transferId);
    public List<Double> getPay(String[] ids,LocalDateTime startDate);
    public List<Double> getRecive(String[] ids, LocalDateTime startDate);
}
