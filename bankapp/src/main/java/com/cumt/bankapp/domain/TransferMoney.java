package com.cumt.bankapp.domain;

import java.io.Serializable;
//import java.math.BigDecimal;
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

    private double amount;

    /**  */

    private String transferDate;

    /**  */

    private String description;

    private String pay;

    private String status;

    private Boolean is;

    private int read;

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

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public double getAmount()
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

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIs() {
        return is;
    }

    public void setIs(Boolean is) {
        this.is = is;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "TransferMoney{" +
                "appointmentTime=" + appointmentTime +
                ", transferId='" + transferId + '\'' +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                ", transferDate='" + transferDate + '\'' +
                ", description='" + description + '\'' +
                ", pay='" + pay + '\'' +
                ", status='" + status + '\'' +
                ", is=" + is +
                ", read=" + read +
                '}';
    }
}
