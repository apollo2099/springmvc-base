package com.base.modules.sys.dto;

public class SysRole {
    private Integer roleId;

    private String roleName;

    private Integer removable;

    private Integer allocatable;

    private String description;

    private Integer status;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getRemovable() {
        return removable;
    }

    public void setRemovable(Integer removable) {
        this.removable = removable;
    }

    public Integer getAllocatable() {
        return allocatable;
    }

    public void setAllocatable(Integer allocatable) {
        this.allocatable = allocatable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}