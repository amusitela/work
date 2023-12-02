package com.cumt.bankapp.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * user_information对象 user_information
 * 
 * @author lyw
 * @date 2023-11-10
 */
public class UserInformation implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**  */

    private String nm;

    /**  */

    private String pswd;

    /**  */
    private String idCard;

    /**  */

    private String phone;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    /**  */
    private String card;

    public void setNm(String nm) 
    {
        this.nm = nm;
    }

    public String getNm() 
    {
        return nm;
    }
    public void setPswd(String pswd) 
    {
        this.pswd = pswd;
    }

    public String getPswd() 
    {
        return pswd;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setCard(String card) 
    {
        this.card = card;
    }

    public String getCard() 
    {
        return card;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "nm='" + nm + '\'' +
                ", pswd='" + pswd + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                ", card='" + card + '\'' +
                '}';
    }
}
