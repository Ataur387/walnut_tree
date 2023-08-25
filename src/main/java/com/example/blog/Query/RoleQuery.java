package com.example.blog.Query;

import com.example.blog.DTO.RoleDTO;
import com.example.blog.Entity.RoleEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

public class RoleQuery {

    public static Specification<RoleEntity> getQuery(RoleDTO dto) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isNotBlank(dto.getRoleType().toString())) {
                predicates.add(cb.equal(root.get("roleType"), dto.getRoleType()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}