package com.example.auth_service.repository;

import com.example.auth_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    public Optional<UserEntity> findById(String id);
}
