package com.ruoyi.bank.service;

import java.util.List;
import com.ruoyi.bank.domain.PublicAccount;

/**
 * PaccountService接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface IPublicAccountService 
{
    /**
     * 查询Paccount
     * 
     * @param accountId Paccount主键
     * @return Paccount
     */
    public PublicAccount selectPublicAccountByAccountId(String accountId);

    /**
     * 查询Paccount列表
     * 
     * @param publicAccount Paccount
     * @return Paccount集合
     */
    public List<PublicAccount> selectPublicAccountList(PublicAccount publicAccount);

    /**
     * 新增Paccount
     * 
     * @param publicAccount Paccount
     * @return 结果
     */
    public int insertPublicAccount(PublicAccount publicAccount);

    /**
     * 修改Paccount
     * 
     * @param publicAccount Paccount
     * @return 结果
     */
    public int updatePublicAccount(PublicAccount publicAccount);

    /**
     * 批量删除Paccount
     * 
     * @param accountIds 需要删除的Paccount主键集合
     * @return 结果
     */
    public int deletePublicAccountByAccountIds(String[] accountIds);

    /**
     * 删除Paccount信息
     * 
     * @param accountId Paccount主键
     * @return 结果
     */
    public int deletePublicAccountByAccountId(String accountId);
}
