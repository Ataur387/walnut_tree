package com.example.blog.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private RoleType roleType;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<UserEntity> users;

    public RoleEntity() {
    }

    public List<UserEntity> getUser() {
        return users;
    }

    public void setUser(UserEntity user) {
        this.getUser().add(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
