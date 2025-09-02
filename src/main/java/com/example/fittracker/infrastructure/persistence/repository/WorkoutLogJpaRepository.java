package com.example.fittracker.infrastructure.persistence.repository;

import com.example.fittracker.infrastructure.persistence.entity.WorkoutLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

// This is my JPA repository interface for accessing user history workout in the database
public interface WorkoutLogJpaRepository extends JpaRepository<WorkoutLogEntity, Long> {
    List<WorkoutLogEntity> findByUser_IdOrderByDateDesc(Long userId);
    List<WorkoutLogEntity> findByUser_IdAndDateBetweenOrderByDateDesc(Long userId, LocalDate from, LocalDate to);
}
