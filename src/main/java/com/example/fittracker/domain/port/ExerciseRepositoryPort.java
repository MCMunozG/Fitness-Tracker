package com.example.fittracker.domain.port;

import com.example.fittracker.domain.model.Exercise;

import java.util.List;
import java.util.Optional;

// I made this port because the domain require an Exercise persistence
public interface ExerciseRepositoryPort {
    List<Exercise> findAll();
    // I made an Optional class to avoid problems with null values
    // Using Optional because the user might not have this attribute
    Optional<Exercise> findByName(String name);
    Exercise save(Exercise e);
}
