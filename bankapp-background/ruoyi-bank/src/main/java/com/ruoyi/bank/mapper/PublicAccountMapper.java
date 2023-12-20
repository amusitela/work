package com.ruoyi.bank.mapper;

import java.util.List;
import com.ruoyi.bank.domain.PublicAccount;
import org.apache.ibatis.annotations.Param;

/**
 * paccountMapper接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface PublicAccountMapper 
{
    /**
     * 查询paccount
     * 
     * @param id paccount主键
     * @return paccount
     */
    public PublicAccount selectPublicAccountById(String id);

    /**
     * 查询paccount
     *
     * @param id paccount主键
     * @return paccount
     */
    public PublicAccount selectPublicAccountByAId(String id);

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
     * 删除paccount
     * 
     * @param id paccount主键
     * @return 结果
     */
    public int deletePublicAccountById(String id);

    /**
     * 批量删除paccount
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePublicAccountByIds(String[] ids);

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

    /**
     * 转账操作
     * @param accountId amount
     * */
    public void withdrawPublic(@Param("accountId") String accountId, @Param("amount") Double amount);

    /**
     * 转账操作
     * @param accountId amount
     * */
    public void depositPublic(@Param("accountId") String accountId, @Param("amount") Double amount);
}
