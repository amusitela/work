package com.ruoyi.bank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bank.mapper.TransferMoneyMapper;
import com.ruoyi.bank.domain.TransferMoney;
import com.ruoyi.bank.service.ITransferMoneyService;

/**
 * Transfer_moneyService业务层处理
 * 
 * @author lyw
 * @date 2023-12-15
 */
@Service
public class TransferMoneyServiceImpl implements ITransferMoneyService 
{
    @Autowired
    private TransferMoneyMapper transferMoneyMapper;

    /**
     * 查询Transfer_money
     * 
     * @param appointmentTime Transfer_money主键
     * @return Transfer_money
     */
    @Override
    public TransferMoney selectTransferMoneyByAppointmentTime(String appointmentTime)
    {
        return transferMoneyMapper.selectTransferMoneyByAppointmentTime(appointmentTime);
    }

    /**
     * 查询Transfer_money列表
     * 
     * @param transferMoney Transfer_money
     * @return Transfer_money
     */
    @Override
    public List<TransferMoney> selectTransferMoneyList(TransferMoney transferMoney)
    {
        return transferMoneyMapper.selectTransferMoneyList(transferMoney);
    }

    /**
     * 新增Transfer_money
     * 
     * @param transferMoney Transfer_money
     * @return 结果
     */
    @Override
    public int insertTransferMoney(TransferMoney transferMoney)
    {
        return transferMoneyMapper.insertTransferMoney(transferMoney);
    }

    /**
     * 修改Transfer_money
     * 
     * @param transferMoney Transfer_money
     * @return 结果
     */
    @Override
    public int updateTransferMoney(TransferMoney transferMoney)
    {
        return transferMoneyMapper.updateTransferMoney(transferMoney);
    }

    /**
     * 批量删除Transfer_money
     * 
     * @param appointmentTimes 需要删除的Transfer_money主键
     * @return 结果
     */
    @Override
    public int deleteTransferMoneyByAppointmentTimes(String[] appointmentTimes)
    {
        return transferMoneyMapper.deleteTransferMoneyByAppointmentTimes(appointmentTimes);
    }

    /**
     * 删除Transfer_money信息
     * 
     * @param appointmentTime Transfer_money主键
     * @return 结果
     */
    @Override
    public int deleteTransferMoneyByAppointmentTime(String appointmentTime)
    {
        return transferMoneyMapper.deleteTransferMoneyByAppointmentTime(appointmentTime);
    }
}
