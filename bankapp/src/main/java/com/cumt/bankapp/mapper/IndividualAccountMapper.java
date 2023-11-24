package com.cumt.bankapp.mapper;

import java.util.List;
import com.cumt.bankapp.domain.IndividualAccount;

/**
 * individual_accountMapper接口
 * 
 * @author lyw
 * @date 2023-11-10
 */
public interface IndividualAccountMapper 
{
    /**
     * 查询individual_account
     * 
     * @param accountId individual_account主键
     * @return individual_account
     */
    public IndividualAccount selectIndividualAccountByAccountId(String accountId);

    /**
     * 查询individual_account列表
     * 
     * @param individualAccount individual_account
     * @return individual_account集合
     */
    public List<IndividualAccount> selectIndividualAccountList(IndividualAccount individualAccount);

    /**
     * 新增individual_account
     * 
     * @param individualAccount individual_account
     * @return 结果
     */
    public int insertIndividualAccount(IndividualAccount individualAccount);

    /**
     * 修改individual_account
     * 
     * @param individualAccount individual_account
     * @return 结果
     */
    public int updateIndividualAccount(IndividualAccount individualAccount);

    /**
     * 删除individual_account
     * 
     * @param accountId individual_account主键
     * @return 结果
     */
    public int deleteIndividualAccountByAccountId(String accountId);

    /**
     * 批量删除individual_account
     * 
     * @param accountIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIndividualAccountByAccountIds(String[] accountIds);
}
