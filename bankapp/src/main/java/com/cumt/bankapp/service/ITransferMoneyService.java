package com.cumt.bankapp.service;

import java.util.List;
import com.cumt.bankapp.domain.TransferMoney;

/**
 * transfer_moneyService接口
 * 
 * @author lyw
 * @date 2023-11-10
 */
public interface ITransferMoneyService 
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
     * 批量删除transfer_money
     * 
     * @param transferIds 需要删除的transfer_money主键集合
     * @return 结果
     */
    public int deleteTransferMoneyByTransferIds(String transferIds);

    /**
     * 删除transfer_money信息
     * 
     * @param transferId transfer_money主键
     * @return 结果
     */
    public int deleteTransferMoneyByTransferId(String transferId);
}
