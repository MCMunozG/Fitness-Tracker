package com.example.fittracker.domain.model;

import java.time.LocalDate;
import java.util.Map;

// This is my Workout History model
public record WorkoutLog(
    Long id,
    Long userId,
    Long workoutId,
    LocalDate date,
    Map<String,Integer> timePerExerciseMinutes,
    Integer totalMinutes,
    Integer calories
) {}
