package com.ruoyi.bank.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * User_information对象 user_information
 * 
 * @author lyw
 * @date 2023-12-15
 */
public class UserInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "")
    private String nm;

    /**  */
    @Excel(name = "")
    private String pswd;

    /**  */
    @Excel(name = "")
    private String idCard;

    /**  */
    @Excel(name = "")
    private String phone;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /**  */
    @Excel(name = "")
    private String card;

    /**  */
    @Excel(name = "")
    private String paypswd;

    /**  */
    private String useId;

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
    public void setPaypswd(String paypswd) 
    {
        this.paypswd = paypswd;
    }

    public String getPaypswd() 
    {
        return paypswd;
    }
    public void setUseId(String useId) 
    {
        this.useId = useId;
    }

    public String getUseId() 
    {
        return useId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("nm", getNm())
            .append("pswd", getPswd())
            .append("idCard", getIdCard())
            .append("phone", getPhone())
            .append("createdAt", getCreatedAt())
            .append("card", getCard())
            .append("paypswd", getPaypswd())
            .append("useId", getUseId())
            .toString();
    }
}
