package com.example.fittracker.infrastructure.persistence.repository;

import com.example.fittracker.infrastructure.persistence.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This is my JPA repository interface for accessing exercise data in the database
public interface ExerciseJpaRepository extends JpaRepository<ExerciseEntity, Long> {
    Optional<ExerciseEntity> findByName(String name);
}
