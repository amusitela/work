package com.cumt.bankapp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * flow对象 flow
 * 
 * @author lyw
 * @date 2023-11-10
 */
public class Flow implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String transactionId;

    /**  */

    private String account1Id;

    /**  */

    private String transactionType;

    /**  */

    private BigDecimal amount;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date transactionDate;

    /**  */

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
        return "Flow{" +
                "transactionId='" + transactionId + '\'' +
                ", account1Id='" + account1Id + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", description='" + description + '\'' +
                '}';
    }
}
