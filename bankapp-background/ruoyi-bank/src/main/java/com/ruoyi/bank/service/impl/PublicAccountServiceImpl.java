package com.ruoyi.bank.service.impl;

import java.util.List;

import com.ruoyi.bank.domain.IndividualAccount;
import com.ruoyi.bank.mapper.IndividualAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bank.mapper.PublicAccountMapper;
import com.ruoyi.bank.domain.PublicAccount;
import com.ruoyi.bank.service.IPublicAccountService;
import org.springframework.transaction.annotation.Transactional;

/**
 * paccountService业务层处理
 * 
 * @author lyw
 * @date 2023-12-15
 */
@Service
public class PublicAccountServiceImpl implements IPublicAccountService
{
    @Autowired
    private PublicAccountMapper publicAccountMapper;

    @Autowired
    private IndividualAccountMapper individualAccountMapper;

    /**
     * 查询paccount
     * 
     * @param id paccount主键
     * @return paccount
     */
    @Override
    public PublicAccount selectPublicAccountById(String id)
    {
        return publicAccountMapper.selectPublicAccountById(id);
    }

    /**
     * 查询paccount列表
     * 
     * @param publicAccount paccount
     * @return paccount
     */
    @Override
    public List<PublicAccount> selectPublicAccountList(PublicAccount publicAccount)
    {
        return publicAccountMapper.selectPublicAccountList(publicAccount);
    }

    /**
     * 新增paccount
     * 
     * @param publicAccount paccount
     * @return 结果
     */
    @Override
    public int insertPublicAccount(PublicAccount publicAccount)
    {
        return publicAccountMapper.insertPublicAccount(publicAccount);
    }

    /**
     * 修改paccount
     * 
     * @param publicAccount paccount
     * @return 结果
     */
    @Override
    public int updatePublicAccount(PublicAccount publicAccount)
    {
        return publicAccountMapper.updatePublicAccount(publicAccount);
    }

    /**
     * 批量删除paccount
     * 
     * @param ids 需要删除的paccount主键
     * @return 结果
     */
    @Override
    public int deletePublicAccountByIds(String[] ids)
    {
        return publicAccountMapper.deletePublicAccountByIds(ids);
    }

    /**
     * 删除paccount信息
     * 
     * @param id paccount主键
     * @return 结果
     */
    @Override
    public int deletePublicAccountById(String id)
    {
        return publicAccountMapper.deletePublicAccountById(id);
    }

    @Override
    @Transactional
    public String transferPtoP(String fromAccountId, String toAccountId, Double amount) {

        PublicAccount IAUser = publicAccountMapper.selectPublicAccountByAId(fromAccountId);
        try {
            PublicAccount IAToUser = publicAccountMapper.selectPublicAccountByAId(toAccountId);
        } catch (Exception e) {
            e.printStackTrace();
            return "转账用户不存在";
        }
        if(Double.valueOf(IAUser.getBalance())<amount){
            return "余额不足";
        }
        try {
            publicAccountMapper.withdrawPublic(fromAccountId,amount);
            publicAccountMapper.depositPublic(toAccountId,amount);
            return "转账成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "转账失败:"+e.getMessage();
        }
    }
    @Override
    @Transactional
    public String transfer(String fromAccountId, String toAccountId, Double amount) {

        IndividualAccount IAUser = individualAccountMapper.selectIndividualAccountById1(fromAccountId);
        try {
            IndividualAccount IAToUser = individualAccountMapper.selectIndividualAccountById1(toAccountId);
        } catch (Exception e) {
            e.printStackTrace();
            return "转账用户不存在";
        }
        if(Double.valueOf(IAUser.getBalance())<amount){
            return "余额不足";
        }
        try {
            publicAccountMapper.withdraw(fromAccountId,amount);
            publicAccountMapper.deposit(toAccountId,amount);
            return "转账成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "转账失败:"+e.getMessage();
        }
    }

    @Override
    @Transactional
    public String transferPtoI(String fromAccountId, String toAccountId, Double amount) {
        PublicAccount IAUser = publicAccountMapper.selectPublicAccountByAId(fromAccountId);
        try {
            IndividualAccount IAToUser = individualAccountMapper.selectIndividualAccountById1(toAccountId);
        } catch (Exception e) {
            e.printStackTrace();
            return "转账用户不存在";
        }
        if(Double.valueOf(IAUser.getBalance())<amount){
            return "余额不足";
        }
        try {
            publicAccountMapper.withdrawPublic(fromAccountId,amount);
            publicAccountMapper.deposit(toAccountId,amount);
            return "转账成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "转账失败:"+e.getMessage();
        }
    }
}
