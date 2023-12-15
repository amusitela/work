package com.ruoyi.bank.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Transfer_money对象 transfer_money
 * 
 * @author lyw
 * @date 2023-12-15
 */
public class TransferMoney extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String appointmentTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String transferId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fromAccount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String toAccount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String amount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String transferDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String description;

    public void setAppointmentTime(String appointmentTime) 
    {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentTime() 
    {
        return appointmentTime;
    }
    public void setTransferId(String transferId) 
    {
        this.transferId = transferId;
    }

    public String getTransferId() 
    {
        return transferId;
    }
    public void setFromAccount(String fromAccount) 
    {
        this.fromAccount = fromAccount;
    }

    public String getFromAccount() 
    {
        return fromAccount;
    }
    public void setToAccount(String toAccount) 
    {
        this.toAccount = toAccount;
    }

    public String getToAccount() 
    {
        return toAccount;
    }
    public void setAmount(String amount) 
    {
        this.amount = amount;
    }

    public String getAmount() 
    {
        return amount;
    }
    public void setTransferDate(String transferDate) 
    {
        this.transferDate = transferDate;
    }

    public String getTransferDate() 
    {
        return transferDate;
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
            .append("appointmentTime", getAppointmentTime())
            .append("transferId", getTransferId())
            .append("fromAccount", getFromAccount())
            .append("toAccount", getToAccount())
            .append("amount", getAmount())
            .append("transferDate", getTransferDate())
            .append("description", getDescription())
            .toString();
    }
}
