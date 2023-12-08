package com.cumt.bankapp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * transfer_money对象 transfer_money
 * 
 * @author lyw
 * @date 2023-11-10
 */
public class TransferMoney implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date appointmentTime;

    /**  */
    private String transferId;

    /**  */

    private String fromAccount;

    /**  */

    private String toAccount;

    /**  */

    private BigDecimal amount;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date transferDate;

    /**  */

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
        return "TransferMoney{" +
                "appointmentTime=" + appointmentTime +
                ", transferId='" + transferId + '\'' +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                ", transferDate=" + transferDate +
                ", description='" + description + '\'' +
                '}';
    }
}
