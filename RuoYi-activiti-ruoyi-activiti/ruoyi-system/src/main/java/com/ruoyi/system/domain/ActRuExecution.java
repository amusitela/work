package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 执行实例对象 act_ru_execution
 *
 * @date 2022-07-13
 */
public class ActRuExecution extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /**  */
    @Excel(name = "")
    private Long rev;

    /**  */
    @Excel(name = "")
    private String procInstId;

    /**  */
    @Excel(name = "")
    private String businessKey;

    /**  */
    @Excel(name = "")
    private String parentId;

    /**  */
    @Excel(name = "")
    private String procDefId;

    /**  */
    @Excel(name = "")
    private String superExec;

    /**  */
    @Excel(name = "")
    private String rootProcInstId;

    /**  */
    @Excel(name = "")
    private String actId;

    /**  */
    @Excel(name = "")
    private Integer isActive;

    /**  */
    @Excel(name = "")
    private Integer isConcurrent;

    /**  */
    @Excel(name = "")
    private Integer isScope;

    /**  */
    @Excel(name = "")
    private Integer isEventScope;

    /**  */
    @Excel(name = "")
    private Integer isMiRoot;

    /**  */
    @Excel(name = "")
    private Long suspensionState;

    /**  */
    @Excel(name = "")
    private Long cachedEntState;

    /**  */
    @Excel(name = "")
    private String tenantId;

    /**  */
    @Excel(name = "")
    private String name;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**  */
    @Excel(name = "")
    private String startUserId;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lockTime;

    /**  */
    @Excel(name = "")
    private Integer isCountEnabled;

    /**  */
    @Excel(name = "")
    private Long evtSubscrCount;

    /**  */
    @Excel(name = "")
    private Long taskCount;

    /**  */
    @Excel(name = "")
    private Long jobCount;

    /**  */
    @Excel(name = "")
    private Long timerJobCount;

    /**  */
    @Excel(name = "")
    private Long suspJobCount;

    /**  */
    @Excel(name = "")
    private Long deadletterJobCount;

    /**  */
    @Excel(name = "")
    private Long varCount;

    /**  */
    @Excel(name = "")
    private Long idLinkCount;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setRev(Long rev) 
    {
        this.rev = rev;
    }

    public Long getRev() 
    {
        return rev;
    }
    public void setProcInstId(String procInstId) 
    {
        this.procInstId = procInstId;
    }

    public String getProcInstId() 
    {
        return procInstId;
    }
    public void setBusinessKey(String businessKey) 
    {
        this.businessKey = businessKey;
    }

    public String getBusinessKey() 
    {
        return businessKey;
    }
    public void setParentId(String parentId) 
    {
        this.parentId = parentId;
    }

    public String getParentId() 
    {
        return parentId;
    }
    public void setProcDefId(String procDefId) 
    {
        this.procDefId = procDefId;
    }

    public String getProcDefId() 
    {
        return procDefId;
    }
    public void setSuperExec(String superExec) 
    {
        this.superExec = superExec;
    }

    public String getSuperExec() 
    {
        return superExec;
    }
    public void setRootProcInstId(String rootProcInstId) 
    {
        this.rootProcInstId = rootProcInstId;
    }

    public String getRootProcInstId() 
    {
        return rootProcInstId;
    }
    public void setActId(String actId) 
    {
        this.actId = actId;
    }

    public String getActId() 
    {
        return actId;
    }
    public void setIsActive(Integer isActive) 
    {
        this.isActive = isActive;
    }

    public Integer getIsActive() 
    {
        return isActive;
    }
    public void setIsConcurrent(Integer isConcurrent) 
    {
        this.isConcurrent = isConcurrent;
    }

    public Integer getIsConcurrent() 
    {
        return isConcurrent;
    }
    public void setIsScope(Integer isScope) 
    {
        this.isScope = isScope;
    }

    public Integer getIsScope() 
    {
        return isScope;
    }
    public void setIsEventScope(Integer isEventScope) 
    {
        this.isEventScope = isEventScope;
    }

    public Integer getIsEventScope() 
    {
        return isEventScope;
    }
    public void setIsMiRoot(Integer isMiRoot) 
    {
        this.isMiRoot = isMiRoot;
    }

    public Integer getIsMiRoot() 
    {
        return isMiRoot;
    }
    public void setSuspensionState(Long suspensionState) 
    {
        this.suspensionState = suspensionState;
    }

    public Long getSuspensionState() 
    {
        return suspensionState;
    }
    public void setCachedEntState(Long cachedEntState) 
    {
        this.cachedEntState = cachedEntState;
    }

    public Long getCachedEntState() 
    {
        return cachedEntState;
    }
    public void setTenantId(String tenantId) 
    {
        this.tenantId = tenantId;
    }

    public String getTenantId() 
    {
        return tenantId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setStartUserId(String startUserId) 
    {
        this.startUserId = startUserId;
    }

    public String getStartUserId() 
    {
        return startUserId;
    }
    public void setLockTime(Date lockTime) 
    {
        this.lockTime = lockTime;
    }

    public Date getLockTime() 
    {
        return lockTime;
    }
    public void setIsCountEnabled(Integer isCountEnabled) 
    {
        this.isCountEnabled = isCountEnabled;
    }

    public Integer getIsCountEnabled() 
    {
        return isCountEnabled;
    }
    public void setEvtSubscrCount(Long evtSubscrCount) 
    {
        this.evtSubscrCount = evtSubscrCount;
    }

    public Long getEvtSubscrCount() 
    {
        return evtSubscrCount;
    }
    public void setTaskCount(Long taskCount) 
    {
        this.taskCount = taskCount;
    }

    public Long getTaskCount() 
    {
        return taskCount;
    }
    public void setJobCount(Long jobCount) 
    {
        this.jobCount = jobCount;
    }

    public Long getJobCount() 
    {
        return jobCount;
    }
    public void setTimerJobCount(Long timerJobCount) 
    {
        this.timerJobCount = timerJobCount;
    }

    public Long getTimerJobCount() 
    {
        return timerJobCount;
    }
    public void setSuspJobCount(Long suspJobCount) 
    {
        this.suspJobCount = suspJobCount;
    }

    public Long getSuspJobCount() 
    {
        return suspJobCount;
    }
    public void setDeadletterJobCount(Long deadletterJobCount) 
    {
        this.deadletterJobCount = deadletterJobCount;
    }

    public Long getDeadletterJobCount() 
    {
        return deadletterJobCount;
    }
    public void setVarCount(Long varCount) 
    {
        this.varCount = varCount;
    }

    public Long getVarCount() 
    {
        return varCount;
    }
    public void setIdLinkCount(Long idLinkCount) 
    {
        this.idLinkCount = idLinkCount;
    }

    public Long getIdLinkCount() 
    {
        return idLinkCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rev", getRev())
            .append("procInstId", getProcInstId())
            .append("businessKey", getBusinessKey())
            .append("parentId", getParentId())
            .append("procDefId", getProcDefId())
            .append("superExec", getSuperExec())
            .append("rootProcInstId", getRootProcInstId())
            .append("actId", getActId())
            .append("isActive", getIsActive())
            .append("isConcurrent", getIsConcurrent())
            .append("isScope", getIsScope())
            .append("isEventScope", getIsEventScope())
            .append("isMiRoot", getIsMiRoot())
            .append("suspensionState", getSuspensionState())
            .append("cachedEntState", getCachedEntState())
            .append("tenantId", getTenantId())
            .append("name", getName())
            .append("startTime", getStartTime())
            .append("startUserId", getStartUserId())
            .append("lockTime", getLockTime())
            .append("isCountEnabled", getIsCountEnabled())
            .append("evtSubscrCount", getEvtSubscrCount())
            .append("taskCount", getTaskCount())
            .append("jobCount", getJobCount())
            .append("timerJobCount", getTimerJobCount())
            .append("suspJobCount", getSuspJobCount())
            .append("deadletterJobCount", getDeadletterJobCount())
            .append("varCount", getVarCount())
            .append("idLinkCount", getIdLinkCount())
            .toString();
    }
}
