package com.exam.manage.asystem.entity;

import java.util.Date;

public class SysAuthority {
    private String authorityId;

    private String authorityName;

    private String authorityRemark;

    private Date createTime;

    private Date updateTime;

    private String authorityContent;

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId == null ? null : authorityId.trim();
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName == null ? null : authorityName.trim();
    }

    public String getAuthorityRemark() {
        return authorityRemark;
    }

    public void setAuthorityRemark(String authorityRemark) {
        this.authorityRemark = authorityRemark == null ? null : authorityRemark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthorityContent() {
        return authorityContent;
    }

    public void setAuthorityContent(String authorityContent) {
        this.authorityContent = authorityContent == null ? null : authorityContent.trim();
    }
}