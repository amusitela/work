package com.ruoyi.bank.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * paccount对象 public_account
 * 
 * @author lyw
 * @date 2023-12-15
 */
public class PublicAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "")
    private String accountId;

    /**  */
    @Excel(name = "")
    private String accountName;

    /**  */
    @Excel(name = "")
    private String accountType;

    /**  */
    @Excel(name = "")
    private String balance;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date openDate;

    /**  */
    @Excel(name = "")
    private String address;

    /**  */
    @Excel(name = "")
    private String phoneNumber;

    /**  */
    @Excel(name = "")
    private String email;

    /**  */
    @Excel(name = "")
    private String isActive;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedAt;

    /**  */
    private String id;

    public void setAccountId(String accountId) 
    {
        this.accountId = accountId;
    }

    public String getAccountId() 
    {
        return accountId;
    }
    public void setAccountName(String accountName) 
    {
        this.accountName = accountName;
    }

    public String getAccountName() 
    {
        return accountName;
    }
    public void setAccountType(String accountType) 
    {
        this.accountType = accountType;
    }

    public String getAccountType() 
    {
        return accountType;
    }
    public void setBalance(String balance) 
    {
        this.balance = balance;
    }

    public String getBalance() 
    {
        return balance;
    }
    public void setOpenDate(Date openDate) 
    {
        this.openDate = openDate;
    }

    public Date getOpenDate() 
    {
        return openDate;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setIsActive(String isActive) 
    {
        this.isActive = isActive;
    }

    public String getIsActive() 
    {
        return isActive;
    }
    public void setLastUpdatedAt(Date lastUpdatedAt) 
    {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Date getLastUpdatedAt() 
    {
        return lastUpdatedAt;
    }
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("accountId", getAccountId())
            .append("accountName", getAccountName())
            .append("accountType", getAccountType())
            .append("balance", getBalance())
            .append("openDate", getOpenDate())
            .append("address", getAddress())
            .append("phoneNumber", getPhoneNumber())
            .append("email", getEmail())
            .append("isActive", getIsActive())
            .append("lastUpdatedAt", getLastUpdatedAt())
            .append("id", getId())
            .toString();
    }
}
