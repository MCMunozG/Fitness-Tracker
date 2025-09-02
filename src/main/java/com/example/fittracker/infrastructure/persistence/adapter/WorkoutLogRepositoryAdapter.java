package com.example.fittracker.infrastructure.persistence.adapter;

import com.example.fittracker.domain.model.WorkoutLog;
import com.example.fittracker.domain.port.WorkoutLogRepositoryPort;
import com.example.fittracker.infrastructure.persistence.entity.UserEntity;
import com.example.fittracker.infrastructure.persistence.entity.WorkoutEntity;
import com.example.fittracker.infrastructure.persistence.entity.WorkoutLogEntity;
import com.example.fittracker.infrastructure.persistence.repository.UserJpaRepository;
import com.example.fittracker.infrastructure.persistence.repository.WorkoutJpaRepository;
import com.example.fittracker.infrastructure.persistence.repository.WorkoutLogJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
// This is my adapter class for managing WorkoutLog persistence using a JPA repository
public class WorkoutLogRepositoryAdapter implements WorkoutLogRepositoryPort {
    private final WorkoutLogJpaRepository repo;
    private final UserJpaRepository users;
    private final WorkoutJpaRepository workouts;
    private static WorkoutLog toDomain(WorkoutLogEntity e){
        return new WorkoutLog(
            e.getId(),
            e.getUser().getId(),
            e.getWorkout().getId(),
            e.getDate(),
            e.getTimesPerExerciseMinutes(),
            e.getTotalMinutes(),
            e.getCalories()
        );
    }
    // Since I used @Builder, I can now build my features
    @Override public WorkoutLog save(WorkoutLog log){
        UserEntity u = users.findById(log.userId()).orElseThrow();
        WorkoutEntity w = workouts.findById(log.workoutId()).orElseThrow();
        WorkoutLogEntity e = WorkoutLogEntity.builder()
            .id(log.id())
            .user(u)
            .workout(w)
            .date(log.date())
            .timesPerExerciseMinutes(log.timePerExerciseMinutes())
            .totalMinutes(log.totalMinutes())
            .calories(log.calories())
            .build();
        return toDomain(repo.save(e));
    }
    @Override public List<WorkoutLog> findByUserId(Long userId){
        return repo.findByUser_IdOrderByDateDesc(userId).stream().map(WorkoutLogRepositoryAdapter::toDomain).collect(Collectors.toList());
    }
    @Override public List<WorkoutLog> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to){
        return repo.findByUser_IdAndDateBetweenOrderByDateDesc(userId, from, to).stream().map(WorkoutLogRepositoryAdapter::toDomain).collect(Collectors.toList());
    }
}