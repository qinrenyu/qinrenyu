package com.exam.manage.asystem.entity;

import java.util.Date;
import java.util.List;

public class SysMenus {
    private String menuId;

    private String menuName;

    private String menuPath;

    private String menuParentId;

    private Date createTime;

    private Date updateTime;
    private String power;

    private String menuSeq;
    private String parentIds;
    private List<SysMenus> childList;
    private String visiable;

    public String getVisiable() {
        return visiable;
    }

    public void setVisiable(String visiable) {
        this.visiable = visiable;
    }

    public List<SysMenus> getChildList() {
        return childList;
    }

    public void setChildList(List<SysMenus> childList) {
        this.childList = childList;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getMenuSeq() {
        return menuSeq;
    }

    public void setMenuSeq(String menuSeq) {
        this.menuSeq = menuSeq;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath == null ? null : menuPath.trim();
    }

    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId == null ? null : menuParentId.trim();
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