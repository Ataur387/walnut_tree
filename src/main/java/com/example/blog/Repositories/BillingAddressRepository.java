package com.example.blog.Repositories;

import com.example.blog.Entity.BillingAddressEntity;
import com.example.blog.Entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository  extends JpaRepository<BillingAddressEntity, String> {
    Page<RoleEntity> findAll(Specification<RoleEntity> query, Pageable pageable);
}
