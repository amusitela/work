package com.cumt.bankapp.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * transfer_money对象 transfer_money
 * 
 * @author lyw
 * @date 2023-11-10
 */
public class TransferMoney extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date appointmentTime;

    /**  */
    private String transferId;

    /**  */
    @Excel(name = "")
    private String fromAccount;

    /**  */
    @Excel(name = "")
    private String toAccount;

    /**  */
    @Excel(name = "")
    private BigDecimal amount;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transferDate;

    /**  */
    @Excel(name = "")
    private String description;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public void setAppointmentTime(Date appointmentTime) 
    {
        this.appointmentTime = appointmentTime;
    }

    public Date getAppointmentTime() 
    {
        return appointmentTime;
    }

    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setTransferDate(Date transferDate) 
    {
        this.transferDate = transferDate;
    }

    public Date getTransferDate() 
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
