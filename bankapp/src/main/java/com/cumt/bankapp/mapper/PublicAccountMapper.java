package com.cumt.bankapp.mapper;

import java.util.List;
import com.cumt.bankapp.domain.PublicAccount;
import org.springframework.stereotype.Repository;

/**
 * public_accountMapper接口
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Repository
public interface PublicAccountMapper 
{
    /**
     * 查询public_account
     * 
     * @param accountId public_account主键
     * @return public_account
     */
    public PublicAccount selectPublicAccountByAccountId(String accountId);

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
     * 删除public_account
     * 
     * @param accountId public_account主键
     * @return 结果
     */
    public int deletePublicAccountByAccountId(String accountId);

    /**
     * 批量删除public_account
     * 
     * @param accountIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePublicAccountByAccountIds(String[] accountIds);
}
