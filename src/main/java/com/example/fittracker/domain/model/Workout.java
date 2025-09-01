package com.example.fittracker.domain.model;

import java.util.List;

// This is my Workout with Exercises model
public record Workout(
    Long id,
    String name,
    String description,
    List<WorkoutExercise> items
) {}
