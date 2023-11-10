package com.cumt.bankapp.service;

import java.util.List;
import com.cumt.bankapp.domain.PublicAccount;

/**
 * public_accountService接口
 * 
 * @author lyw
 * @date 2023-11-10
 */
public interface IPublicAccountService 
{
    /**
     * 查询public_account
     * 
     * @param accountId public_account主键
     * @return public_account
     */
    public PublicAccount selectPublicAccountByAccountId(Integer accountId);

    /**
     * 查询public_account列表
     * 
     * @param publicAccount public_account
     * @return public_account集合
     */
    public List<PublicAccount> selectPublicAccountList(PublicAccount publicAccount);

    /**
     * 新增public_account
     * 
     * @param publicAccount public_account
     * @return 结果
     */
    public int insertPublicAccount(PublicAccount publicAccount);

    /**
     * 修改public_account
     * 
     * @param publicAccount public_account
     * @return 结果
     */
    public int updatePublicAccount(PublicAccount publicAccount);

    /**
     * 批量删除public_account
     * 
     * @param accountIds 需要删除的public_account主键集合
     * @return 结果
     */
    public int deletePublicAccountByAccountIds(String accountIds);

    /**
     * 删除public_account信息
     * 
     * @param accountId public_account主键
     * @return 结果
     */
    public int deletePublicAccountByAccountId(Integer accountId);
}
