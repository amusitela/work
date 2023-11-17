package com.cumt.bankapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cumt.bankapp.mapper.PublicAccountMapper;
import com.cumt.bankapp.domain.PublicAccount;
import com.cumt.bankapp.service.IPublicAccountService;
import com.ruoyi.common.core.text.Convert;

/**
 * public_accountService业务层处理
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Service
public class PublicAccountServiceImpl implements IPublicAccountService 
{
    @Autowired
    private PublicAccountMapper publicAccountMapper;

    /**
     * 查询public_account
     * 
     * @param accountId public_account主键
     * @return public_account
     */
    @Override
    public PublicAccount selectPublicAccountByAccountId(String accountId)
    {
        return publicAccountMapper.selectPublicAccountByAccountId(accountId);
    }

    /**
     * 查询public_account列表
     * 
     * @param publicAccount public_account
     * @return public_account
     */
    @Override
    public List<PublicAccount> selectPublicAccountList(PublicAccount publicAccount)
    {
        return publicAccountMapper.selectPublicAccountList(publicAccount);
    }

    /**
     * 新增public_account
     * 
     * @param publicAccount public_account
     * @return 结果
     */
    @Override
    public int insertPublicAccount(PublicAccount publicAccount)
    {
        return publicAccountMapper.insertPublicAccount(publicAccount);
    }

    /**
     * 修改public_account
     * 
     * @param publicAccount public_account
     * @return 结果
     */
    @Override
    public int updatePublicAccount(PublicAccount publicAccount)
    {
        return publicAccountMapper.updatePublicAccount(publicAccount);
    }

    /**
     * 批量删除public_account
     * 
     * @param accountIds 需要删除的public_account主键
     * @return 结果
     */
    @Override
    public int deletePublicAccountByAccountIds(String accountIds)
    {
        return publicAccountMapper.deletePublicAccountByAccountIds(Convert.toStrArray(accountIds));
    }

    /**
     * 删除public_account信息
     * 
     * @param accountId public_account主键
     * @return 结果
     */
    @Override
    public int deletePublicAccountByAccountId(String accountId)
    {
        return publicAccountMapper.deletePublicAccountByAccountId(accountId);
    }
}
