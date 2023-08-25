package com.example.blog.Services;

import com.example.blog.DTO.RoleDTO;
import com.example.blog.Entity.RoleEntity;
import com.example.blog.Query.RoleQuery;
import com.example.blog.Repositories.RoleRepository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Page<RoleEntity> findRoles(RoleDTO roleDTO){
        Page<RoleEntity> roles = roleRepository.findAll(RoleQuery.getQuery(roleDTO), Page.empty().getPageable());
        return roles;
    }
}
