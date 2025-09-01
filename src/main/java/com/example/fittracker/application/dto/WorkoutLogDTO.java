package com.example.fittracker.application.dto;

import java.time.LocalDate;
import java.util.Map;

// This is my DTO for exposing workout log data outside the domain layer
public record WorkoutLogDTO(
    Long id,
    LocalDate date,
    String workoutName,
    Map<String, Integer> times,
    Integer totalMinutes,
    Integer calories
) {}
