package com.ruoyi.bank.service;

import java.util.List;
import com.ruoyi.bank.domain.PublicAccount;

/**
 * paccountService接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface IPublicAccountService 
{
    /**
     * 查询paccount
     * 
     * @param id paccount主键
     * @return paccount
     */
    public PublicAccount selectPublicAccountById(String id);

    /**
     * 查询paccount列表
     * 
     * @param publicAccount paccount
     * @return paccount集合
     */
    public List<PublicAccount> selectPublicAccountList(PublicAccount publicAccount);

    /**
     * 新增paccount
     * 
     * @param publicAccount paccount
     * @return 结果
     */
    public int insertPublicAccount(PublicAccount publicAccount);

    /**
     * 修改paccount
     * 
     * @param publicAccount paccount
     * @return 结果
     */
    public int updatePublicAccount(PublicAccount publicAccount);

    /**
     * 批量删除paccount
     * 
     * @param ids 需要删除的paccount主键集合
     * @return 结果
     */
    public int deletePublicAccountByIds(String[] ids);

    /**
     * 删除paccount信息
     * 
     * @param id paccount主键
     * @return 结果
     */
    public int deletePublicAccountById(String id);

    public String transfer(String fromAccountId, String toAccountId, Double amount);

    public String transferPtoP(String fromAccountId, String toAccountId, Double amount);

    public String transferPtoI(String fromAccountId, String toAccountId, Double amount);
}
