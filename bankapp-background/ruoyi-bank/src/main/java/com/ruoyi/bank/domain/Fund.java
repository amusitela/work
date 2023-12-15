package com.ruoyi.bank.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Fund对象 fund
 * 
 * @author lyw
 * @date 2023-12-15
 */
public class Fund extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dayIncomeRatio;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String productName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fundTypeName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String productRiskLevel;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String accumulativeNav;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String productId;

    public void setDayIncomeRatio(String dayIncomeRatio) 
    {
        this.dayIncomeRatio = dayIncomeRatio;
    }

    public String getDayIncomeRatio() 
    {
        return dayIncomeRatio;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setFundTypeName(String fundTypeName) 
    {
        this.fundTypeName = fundTypeName;
    }

    public String getFundTypeName() 
    {
        return fundTypeName;
    }
    public void setProductRiskLevel(String productRiskLevel) 
    {
        this.productRiskLevel = productRiskLevel;
    }

    public String getProductRiskLevel() 
    {
        return productRiskLevel;
    }
    public void setAccumulativeNav(String accumulativeNav) 
    {
        this.accumulativeNav = accumulativeNav;
    }

    public String getAccumulativeNav() 
    {
        return accumulativeNav;
    }
    public void setProductId(String productId) 
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dayIncomeRatio", getDayIncomeRatio())
            .append("productName", getProductName())
            .append("fundTypeName", getFundTypeName())
            .append("productRiskLevel", getProductRiskLevel())
            .append("accumulativeNav", getAccumulativeNav())
            .append("productId", getProductId())
            .toString();
    }
}
