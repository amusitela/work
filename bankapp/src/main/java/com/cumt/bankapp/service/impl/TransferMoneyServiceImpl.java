package com.cumt.bankapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.mapper.IndividualAccountMapper;
import com.cumt.bankapp.mapper.UserInformationMapper;
import com.cumt.common.Convert;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cumt.bankapp.mapper.TransferMoneyMapper;
import com.cumt.bankapp.domain.TransferMoney;
import com.cumt.bankapp.service.ITransferMoneyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * transfer_moneyService业务层处理
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Service
public class TransferMoneyServiceImpl implements ITransferMoneyService
{
    @Autowired
    private TransferMoneyMapper transferMoneyMapper;
    @Autowired
    private UserInformationMapper userInformationMapper;

    /**
     * 查询transfer_money
     * 
     * @param transferId transfer_money主键
     * @return transfer_money
     */
    @Override
    public TransferMoney selectTransferMoneyByTransferId(String transferId)
    {
        return transferMoneyMapper.selectTransferMoneyByTransferId(transferId);
    }

    /**
     * 查询transfer_money列表
     * 
     * @param transferMoney transfer_money
     * @return transfer_money
     */
    @Override
    public List<TransferMoney> selectTransferMoneyList(TransferMoney transferMoney)
    {
        return transferMoneyMapper.selectTransferMoneyList(transferMoney);
    }

    /**
     * 新增transfer_money
     * 
     * @param transferMoney transfer_money
     * @return 结果
     */
    @Override
    public int insertTransferMoney(TransferMoney transferMoney)
    {
        return transferMoneyMapper.insertTransferMoney(transferMoney);
    }

    /**
     * 修改transfer_money
     * 
     * @param transferMoney transfer_money
     * @return 结果
     */
    @Override
    public int updateTransferMoney(TransferMoney transferMoney)
    {
        return transferMoneyMapper.updateTransferMoney(transferMoney);
    }

    /**
     * 批量删除transfer_money
     * 
     * @param transferIds 需要删除的transfer_money主键
     * @return 结果
     */
    @Override
    public int deleteTransferMoneyByTransferIds(String transferIds)
    {
        return transferMoneyMapper.deleteTransferMoneyByTransferIds(Convert.toStrArray(transferIds));
    }

    /**
     * 删除transfer_money信息
     * 
     * @param transferId transfer_money主键
     * @return 结果
     */
    @Override
    public int deleteTransferMoneyByTransferId(String transferId)
    {
        return transferMoneyMapper.deleteTransferMoneyByTransferId(transferId);
    }

    @Override
    public List<TransferMoney> selectAllFlow(String transferId) {
        return transferMoneyMapper.selectAllFlow(transferId);
    }

    @Override
    public Double getPay(String id) {
        UserInformation userInformation = userInformationMapper.selectUserInformationByIdCard(id);
        String[] ids=userInformation.getCard().split(",");
        LocalDateTime currentDate = LocalDateTime.now();

        // Calculate the start date for the last seven days
        LocalDateTime startDate = currentDate.minusDays(7);
        List<Double> ts=transferMoneyMapper.getPay(ids,startDate);

        Double t=new Double(0);
        for(var i:ts){
           t=t+i;
        }
        System.out.println(t);
        return t;
    }

    @Override
    public Double getRecive(String id) {
        UserInformation userInformation = userInformationMapper.selectUserInformationByIdCard(id);
        String[] ids=userInformation.getCard().split(",");
        LocalDateTime currentDate = LocalDateTime.now();

        // Calculate the start date for the last seven days
        LocalDateTime startDate = currentDate.minusDays(7);
        List<Double> ts=transferMoneyMapper.getRecive(ids,startDate);
        Double t=new Double(0);
        for(var i:ts){
            t=t+i;
        }
        System.out.println(t);
        return t;
    }

    @Override
    public List<TransferMoney> getRead(String toAccount) {
        return transferMoneyMapper.getRead(toAccount);
    }
}
