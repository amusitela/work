package com.ruoyi.bank.mapper;

import java.util.List;
import com.ruoyi.bank.domain.Fund;

/**
 * FundMapper接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface FundMapper 
{
    /**
     * 查询Fund
     * 
     * @param dayIncomeRatio Fund主键
     * @return Fund
     */
    public Fund selectFundByDayIncomeRatio(String dayIncomeRatio);

    /**
     * 查询Fund列表
     * 
     * @param fund Fund
     * @return Fund集合
     */
    public List<Fund> selectFundList(Fund fund);

    /**
     * 新增Fund
     * 
     * @param fund Fund
     * @return 结果
     */
    public int insertFund(Fund fund);

    /**
     * 修改Fund
     * 
     * @param fund Fund
     * @return 结果
     */
    public int updateFund(Fund fund);

    /**
     * 删除Fund
     * 
     * @param dayIncomeRatio Fund主键
     * @return 结果
     */
    public int deleteFundByDayIncomeRatio(String dayIncomeRatio);

    /**
     * 批量删除Fund
     * 
     * @param dayIncomeRatios 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFundByDayIncomeRatios(String[] dayIncomeRatios);
}
