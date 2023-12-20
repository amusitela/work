package com.cumt.bankapp.mapper;

import java.util.List;
import com.cumt.bankapp.domain.IndividualAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * individual_accountMapper接口
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Repository
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
     * 查询individual_account列表
     *
     * @param
     * @return individual_account集合
     */
    public List<String> selectInActCard( );

    /**
     * 查询individual_account
     *
     * @param individualAccount individual_account
     * @return individual_account
     */
    public IndividualAccount selectIndividualAccount(IndividualAccount individualAccount);

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


    /**
     * 转账操作
     * @param accountId amount
     * */
    public void withdraw(@Param("accountId") String accountId, @Param("amount") Double amount);

    /**
     * 转账操作
     * @param accountId amount
     * */
    public void deposit(@Param("accountId") String accountId, @Param("amount") Double amount);
}
