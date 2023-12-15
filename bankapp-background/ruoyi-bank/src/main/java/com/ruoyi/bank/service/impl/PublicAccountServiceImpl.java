package com.ruoyi.bank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bank.mapper.PublicAccountMapper;
import com.ruoyi.bank.domain.PublicAccount;
import com.ruoyi.bank.service.IPublicAccountService;

/**
 * PaccountService业务层处理
 * 
 * @author lyw
 * @date 2023-12-15
 */
@Service
public class PublicAccountServiceImpl implements IPublicAccountService 
{
    @Autowired
    private PublicAccountMapper publicAccountMapper;

    /**
     * 查询Paccount
     * 
     * @param accountId Paccount主键
     * @return Paccount
     */
    @Override
    public PublicAccount selectPublicAccountByAccountId(String accountId)
    {
        return publicAccountMapper.selectPublicAccountByAccountId(accountId);
    }

    /**
     * 查询Paccount列表
     * 
     * @param publicAccount Paccount
     * @return Paccount
     */
    @Override
    public List<PublicAccount> selectPublicAccountList(PublicAccount publicAccount)
    {
        return publicAccountMapper.selectPublicAccountList(publicAccount);
    }

    /**
     * 新增Paccount
     * 
     * @param publicAccount Paccount
     * @return 结果
     */
    @Override
    public int insertPublicAccount(PublicAccount publicAccount)
    {
        return publicAccountMapper.insertPublicAccount(publicAccount);
    }

    /**
     * 修改Paccount
     * 
     * @param publicAccount Paccount
     * @return 结果
     */
    @Override
    public int updatePublicAccount(PublicAccount publicAccount)
    {
        return publicAccountMapper.updatePublicAccount(publicAccount);
    }

    /**
     * 批量删除Paccount
     * 
     * @param accountIds 需要删除的Paccount主键
     * @return 结果
     */
    @Override
    public int deletePublicAccountByAccountIds(String[] accountIds)
    {
        return publicAccountMapper.deletePublicAccountByAccountIds(accountIds);
    }

    /**
     * 删除Paccount信息
     * 
     * @param accountId Paccount主键
     * @return 结果
     */
    @Override
    public int deletePublicAccountByAccountId(String accountId)
    {
        return publicAccountMapper.deletePublicAccountByAccountId(accountId);
    }
}
