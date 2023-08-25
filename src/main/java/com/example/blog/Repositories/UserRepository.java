package com.example.blog.Repositories;

import com.example.blog.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, String> {

}
