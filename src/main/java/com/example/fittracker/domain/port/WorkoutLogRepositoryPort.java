package com.example.fittracker.domain.port;

import com.example.fittracker.domain.model.WorkoutLog;

import java.time.LocalDate;
import java.util.List;

// I made this port because the domain require a Workout History persistence
public interface WorkoutLogRepositoryPort {
    List<WorkoutLog> findByUserId(Long userId);
    List<WorkoutLog> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to);
    WorkoutLog save(WorkoutLog log);
}
