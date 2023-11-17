package com.cumt.bankapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cumt.bankapp.mapper.IndividualAccountMapper;
import com.cumt.bankapp.domain.IndividualAccount;
import com.cumt.bankapp.service.IIndividualAccountService;
import com.ruoyi.common.core.text.Convert;

/**
 * individual_accountService业务层处理
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Service
public class IndividualAccountServiceImpl implements IIndividualAccountService 
{
    @Autowired
    private IndividualAccountMapper individualAccountMapper;

    /**
     * 查询individual_account
     * 
     * @param accountId individual_account主键
     * @return individual_account
     */
    @Override
    public IndividualAccount selectIndividualAccountByAccountId(String accountId)
    {
        return individualAccountMapper.selectIndividualAccountByAccountId(accountId);
    }

    /**
     * 查询individual_account列表
     * 
     * @param individualAccount individual_account
     * @return individual_account
     */
    @Override
    public List<IndividualAccount> selectIndividualAccountList(IndividualAccount individualAccount)
    {
        return individualAccountMapper.selectIndividualAccountList(individualAccount);
    }

    /**
     * 新增individual_account
     * 
     * @param individualAccount individual_account
     * @return 结果
     */
    @Override
    public int insertIndividualAccount(IndividualAccount individualAccount)
    {
        return individualAccountMapper.insertIndividualAccount(individualAccount);
    }

    /**
     * 修改individual_account
     * 
     * @param individualAccount individual_account
     * @return 结果
     */
    @Override
    public int updateIndividualAccount(IndividualAccount individualAccount)
    {
        return individualAccountMapper.updateIndividualAccount(individualAccount);
    }

    /**
     * 批量删除individual_account
     * 
     * @param accountIds 需要删除的individual_account主键
     * @return 结果
     */
    @Override
    public int deleteIndividualAccountByAccountIds(String accountIds)
    {
        return individualAccountMapper.deleteIndividualAccountByAccountIds(Convert.toStrArray(accountIds));
    }

    /**
     * 删除individual_account信息
     * 
     * @param accountId individual_account主键
     * @return 结果
     */
    @Override
    public int deleteIndividualAccountByAccountId(String accountId)
    {
        return individualAccountMapper.deleteIndividualAccountByAccountId(accountId);
    }
}
