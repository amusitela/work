package com.ruoyi.bank.service;

import java.util.List;
import com.ruoyi.bank.domain.TransferMoney;

/**
 * Transfer_moneyService接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface ITransferMoneyService 
{
    /**
     * 查询Transfer_money
     * 
     * @param id Transfer_money主键
     * @return Transfer_money
     */
    public TransferMoney selectTransferMoneyById(String id);

    /**
     * 查询Transfer_money列表
     * 
     * @param transferMoney Transfer_money
     * @return Transfer_money集合
     */
    public List<TransferMoney> selectTransferMoneyList(TransferMoney transferMoney);

    /**
     * 新增Transfer_money
     * 
     * @param transferMoney Transfer_money
     * @return 结果
     */
    public int insertTransferMoney(TransferMoney transferMoney);

    /**
     * 修改Transfer_money
     * 
     * @param transferMoney Transfer_money
     * @return 结果
     */
    public int updateTransferMoney(TransferMoney transferMoney);

    /**
     * 批量删除Transfer_money
     * 
     * @param ids 需要删除的Transfer_money主键集合
     * @return 结果
     */
    public int deleteTransferMoneyByIds(String[] ids);

    /**
     * 删除Transfer_money信息
     * 
     * @param id Transfer_money主键
     * @return 结果
     */
    public int deleteTransferMoneyById(String id);
}
