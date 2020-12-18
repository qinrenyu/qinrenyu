package com.exam.manage.asystem.entity;

import java.util.Date;

public class SysAuthorityMenu {
    private String authorityMenuId;

    private String authorityId;

    private String menuId;

    private String power;
    private Date createTime;

    private Date updateTime;

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAuthorityMenuId() {
        return authorityMenuId;
    }

    public void setAuthorityMenuId(String authorityMenuId) {
        this.authorityMenuId = authorityMenuId == null ? null : authorityMenuId.trim();
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId == null ? null : authorityId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
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
}