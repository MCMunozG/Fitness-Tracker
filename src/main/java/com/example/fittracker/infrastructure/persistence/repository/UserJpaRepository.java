package com.example.fittracker.infrastructure.persistence.repository;

import com.example.fittracker.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This is my JPA repository interface for accessing user data in the database
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    long countByRole(String role);
}
