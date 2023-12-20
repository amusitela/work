package com.ruoyi.bank.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * chart对象 chart
 * 
 * @author lyw
 * @date 2023-12-15
 */
public class Chart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dwfeRateLJ;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dyejiLjSYL;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ratingSimilarAve;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ratioLJ;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDwfeRateLJ(String dwfeRateLJ) 
    {
        this.dwfeRateLJ = dwfeRateLJ;
    }

    public String getDwfeRateLJ() 
    {
        return dwfeRateLJ;
    }
    public void setDyejiLjSYL(String dyejiLjSYL) 
    {
        this.dyejiLjSYL = dyejiLjSYL;
    }

    public String getDyejiLjSYL() 
    {
        return dyejiLjSYL;
    }
    public void setRatingSimilarAve(String ratingSimilarAve) 
    {
        this.ratingSimilarAve = ratingSimilarAve;
    }

    public String getRatingSimilarAve() 
    {
        return ratingSimilarAve;
    }
    public void setRatioLJ(String ratioLJ) 
    {
        this.ratioLJ = ratioLJ;
    }

    public String getRatioLJ() 
    {
        return ratioLJ;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dwfeRateLJ", getDwfeRateLJ())
            .append("dyejiLjSYL", getDyejiLjSYL())
            .append("ratingSimilarAve", getRatingSimilarAve())
            .append("ratioLJ", getRatioLJ())
            .toString();
    }
}
