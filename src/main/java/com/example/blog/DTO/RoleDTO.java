package com.example.blog.DTO;

import com.example.blog.Entity.RoleType;

public class RoleDTO {
    private RoleType roleType;
    public RoleType getRoleType(){
        return this.roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
