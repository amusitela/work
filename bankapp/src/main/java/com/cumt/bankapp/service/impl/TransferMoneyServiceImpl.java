package com.cumt.bankapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cumt.bankapp.mapper.IndividualAccountMapper;
import com.cumt.common.Convert;
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

    @Override
    public List<TransferMoney> selectAllFlow(String id) {
        String ids= transferMoneyMapper.selectAllCard(id);
        String[] idList=ids.split(",");
        List<TransferMoney> transferMoneyList=new ArrayList<>();
        transferMoneyList=transferMoneyMapper.selectAllFlow(idList);
        return  transferMoneyList;
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


}
