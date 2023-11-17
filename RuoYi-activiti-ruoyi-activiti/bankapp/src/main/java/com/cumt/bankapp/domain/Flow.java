package com.cumt.bankapp.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * flow对象 flow
 * 
 * @author lyw
 * @date 2023-11-10
 */
public class Flow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String transactionId;

    /**  */
    @Excel(name = "")
    private String account1Id;

    /**  */
    @Excel(name = "")
    private String transactionType;

    /**  */
    @Excel(name = "")
    private BigDecimal amount;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transactionDate;

    /**  */
    @Excel(name = "")
    private String description;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccount1Id() {
        return account1Id;
    }

    public void setAccount1Id(String account1Id) {
        this.account1Id = account1Id;
    }


    public void setTransactionType(String transactionType) 
    {
        this.transactionType = transactionType;
    }

    public String getTransactionType() 
    {
        return transactionType;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setTransactionDate(Date transactionDate) 
    {
        this.transactionDate = transactionDate;
    }

    public Date getTransactionDate() 
    {
        return transactionDate;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("transactionId", getTransactionId())
            .append("account1Id", getAccount1Id())
            .append("transactionType", getTransactionType())
            .append("amount", getAmount())
            .append("transactionDate", getTransactionDate())
            .append("description", getDescription())
            .toString();
    }
}
