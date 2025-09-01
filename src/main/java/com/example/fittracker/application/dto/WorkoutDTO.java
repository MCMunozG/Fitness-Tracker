package com.example.fittracker.application.dto;

import java.util.List;

// This is my DTO for exposing workout data outside the domain layer
public record WorkoutDTO(
    Long id,
    String name,
    String description,
    List<WorkoutExerciseDTO> items
) {}
