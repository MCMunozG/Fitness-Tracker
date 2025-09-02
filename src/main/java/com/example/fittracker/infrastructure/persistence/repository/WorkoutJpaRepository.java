package com.example.fittracker.infrastructure.persistence.repository;

import com.example.fittracker.infrastructure.persistence.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This is my JPA repository interface for accessing workout data in the database
public interface WorkoutJpaRepository extends JpaRepository<WorkoutEntity, Long> {
    Optional<WorkoutEntity> findByName(String name);
}
