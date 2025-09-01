package com.example.fittracker.application.dto;

// This is my DTO for exposing exercise data outside the domain layer
public record ExerciseDTO(
    Long id,
    String name,
    String description
) {}
