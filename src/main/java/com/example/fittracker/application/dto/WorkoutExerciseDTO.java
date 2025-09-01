package com.example.fittracker.application.dto;

// This is my DTO for exposing workout exercise data outside the domain layer
public record WorkoutExerciseDTO(
    String exerciseName,
    Integer sets,
    Integer reps
) {}
