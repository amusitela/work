package com.ruoyi.bank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bank.mapper.FundMapper;
import com.ruoyi.bank.domain.Fund;
import com.ruoyi.bank.service.IFundService;

/**
 * FundService业务层处理
 * 
 * @author lyw
 * @date 2023-12-15
 */
@Service
public class FundServiceImpl implements IFundService 
{
    @Autowired
    private FundMapper fundMapper;

    /**
     * 查询Fund
     * 
     * @param dayIncomeRatio Fund主键
     * @return Fund
     */
    @Override
    public Fund selectFundByDayIncomeRatio(String dayIncomeRatio)
    {
        return fundMapper.selectFundByDayIncomeRatio(dayIncomeRatio);
    }

    /**
     * 查询Fund列表
     * 
     * @param fund Fund
     * @return Fund
     */
    @Override
    public List<Fund> selectFundList(Fund fund)
    {
        return fundMapper.selectFundList(fund);
    }

    /**
     * 新增Fund
     * 
     * @param fund Fund
     * @return 结果
     */
    @Override
    public int insertFund(Fund fund)
    {
        return fundMapper.insertFund(fund);
    }

    /**
     * 修改Fund
     * 
     * @param fund Fund
     * @return 结果
     */
    @Override
    public int updateFund(Fund fund)
    {
        return fundMapper.updateFund(fund);
    }

    /**
     * 批量删除Fund
     * 
     * @param dayIncomeRatios 需要删除的Fund主键
     * @return 结果
     */
    @Override
    public int deleteFundByDayIncomeRatios(String[] dayIncomeRatios)
    {
        return fundMapper.deleteFundByDayIncomeRatios(dayIncomeRatios);
    }

    /**
     * 删除Fund信息
     * 
     * @param dayIncomeRatio Fund主键
     * @return 结果
     */
    @Override
    public int deleteFundByDayIncomeRatio(String dayIncomeRatio)
    {
        return fundMapper.deleteFundByDayIncomeRatio(dayIncomeRatio);
    }
}
