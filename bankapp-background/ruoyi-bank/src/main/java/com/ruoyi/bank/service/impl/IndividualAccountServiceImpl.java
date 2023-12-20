package com.ruoyi.bank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bank.mapper.IndividualAccountMapper;
import com.ruoyi.bank.domain.IndividualAccount;
import com.ruoyi.bank.service.IIndividualAccountService;

/**
 * AccountService业务层处理
 * 
 * @author lyw
 * @date 2023-12-15
 */
@Service
public class IndividualAccountServiceImpl implements IIndividualAccountService 
{
    @Autowired
    private IndividualAccountMapper individualAccountMapper;

    /**
     * 查询Account
     * 
     * @param id Account主键
     * @return Account
     */
    @Override
    public IndividualAccount selectIndividualAccountById(String id)
    {
        return individualAccountMapper.selectIndividualAccountById(id);
    }

    /**
     * 查询Account列表
     * 
     * @param individualAccount Account
     * @return Account
     */
    @Override
    public List<IndividualAccount> selectIndividualAccountList(IndividualAccount individualAccount)
    {
        return individualAccountMapper.selectIndividualAccountList(individualAccount);
    }

    /**
     * 新增Account
     * 
     * @param individualAccount Account
     * @return 结果
     */
    @Override
    public int insertIndividualAccount(IndividualAccount individualAccount)
    {
        return individualAccountMapper.insertIndividualAccount(individualAccount);
    }

    /**
     * 修改Account
     * 
     * @param individualAccount Account
     * @return 结果
     */
    @Override
    public int updateIndividualAccount(IndividualAccount individualAccount)
    {
        return individualAccountMapper.updateIndividualAccount(individualAccount);
    }

    /**
     * 批量删除Account
     * 
     * @param ids 需要删除的Account主键
     * @return 结果
     */
    @Override
    public int deleteIndividualAccountByIds(String[] ids)
    {
        return individualAccountMapper.deleteIndividualAccountByIds(ids);
    }

    /**
     * 删除Account信息
     * 
     * @param id Account主键
     * @return 结果
     */
    @Override
    public int deleteIndividualAccountById(String id)
    {
        return individualAccountMapper.deleteIndividualAccountById(id);
    }
}
