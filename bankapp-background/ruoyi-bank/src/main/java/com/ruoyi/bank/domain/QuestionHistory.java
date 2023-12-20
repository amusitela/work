package com.ruoyi.bank.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 历史记录对象 question_history
 * 
 * @author ljn
 * @date 2023-12-15
 */
public class QuestionHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息id */
    private Long id;

    /** 接收人 */
    @Excel(name = "接收人")
    private String receiver;

    /** 发送人 */
    @Excel(name = "发送人")
    private String sender;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @Excel(name = "是否已读")
    private String ifread;

    public String getRead() {
        return ifread;
    }

    public void setRead(String read) {
        this.ifread = read;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setReceiver(String receiver) 
    {
        this.receiver = receiver;
    }

    public String getReceiver() 
    {
        return receiver;
    }
    public void setSender(String sender) 
    {
        this.sender = sender;
    }

    public String getSender() 
    {
        return sender;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }

    @Override
    public String toString() {
        return "QuestionHistory{" +
                "id=" + id +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", read='" + ifread + '\'' +
                '}';
    }
}
