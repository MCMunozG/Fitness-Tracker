package com.example.fittracker.domain.model;

// This is my Exercise per Workout model
public record WorkoutExercise(
    Exercise exercise,
    Integer sets,
    Integer reps
) {}
