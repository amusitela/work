package com.cumt.bankapp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * public_account对象 public_account
 * 
 * @author lyw
 * @date 2023-11-10
 */
public class PublicAccount implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String accountId;

    /**  */

    private String accountName;

    /**  */

    private String accountType;

    /**  */

    private BigDecimal balance;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date openDate;

    /**  */

    private String address;

    /**  */

    private String phoneNumber;

    /**  */

    private String email;

    /**  */

    private Integer isActive;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date lastUpdatedAt;

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
    public void setBalance(BigDecimal balance) 
    {
        this.balance = balance;
    }

    public BigDecimal getBalance() 
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
    public void setIsActive(Integer isActive) 
    {
        this.isActive = isActive;
    }

    public Integer getIsActive() 
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

    @Override
    public String toString() {
        return "PublicAccount{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", openDate=" + openDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}
