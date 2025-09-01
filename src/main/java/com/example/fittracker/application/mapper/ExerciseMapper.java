package com.example.fittracker.application.mapper;

import com.example.fittracker.application.dto.ExerciseDTO;
import com.example.fittracker.domain.model.Exercise;

// This is my mapper for converting Exercise domain model into ExerciseDTO
public class ExerciseMapper {
    public static ExerciseDTO toDTO(Exercise e) {
        return new ExerciseDTO(
            e.id(),
            e.name(),
            e.description()
        );
    }
}
