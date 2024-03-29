package com.ruoyi.bank.mapper;

import java.util.List;
import com.ruoyi.bank.domain.IndividualAccount;

/**
 * AccountMapper接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface IndividualAccountMapper 
{
    /**
     * 查询Account
     * 
     * @param id Account主键
     * @return Account
     */
    public IndividualAccount selectIndividualAccountById(String id);

    /**
     * 查询Account
     *
     * @param id Account主键
     * @return Account
     */
    public IndividualAccount selectIndividualAccountById1(String id);

    /**
     * 查询Account列表
     * 
     * @param individualAccount Account
     * @return Account集合
     */
    public List<IndividualAccount> selectIndividualAccountList(IndividualAccount individualAccount);

    /**
     * 新增Account
     * 
     * @param individualAccount Account
     * @return 结果
     */
    public int insertIndividualAccount(IndividualAccount individualAccount);

    /**
     * 修改Account
     * 
     * @param individualAccount Account
     * @return 结果
     */
    public int updateIndividualAccount(IndividualAccount individualAccount);

    /**
     * 删除Account
     * 
     * @param id Account主键
     * @return 结果
     */
    public int deleteIndividualAccountById(String id);

    /**
     * 批量删除Account
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIndividualAccountByIds(String[] ids);
}
