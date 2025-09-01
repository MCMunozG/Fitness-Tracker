package com.example.fittracker.application.mapper;

import com.example.fittracker.application.dto.WorkoutLogDTO;
import com.example.fittracker.domain.model.Workout;
import com.example.fittracker.domain.model.WorkoutLog;

// This is my mapper for converting WorkoutLog domain model into WorkoutLogDTO
public class WorkoutLogMapper {
    public static WorkoutLogDTO toDTO(WorkoutLog log, Workout workout) {
        return new WorkoutLogDTO(
            log.id(),
            log.date(),
            workout.name(),
            log.timePerExerciseMinutes(),
            log.totalMinutes(),
            log.calories()
        );
    }
}
