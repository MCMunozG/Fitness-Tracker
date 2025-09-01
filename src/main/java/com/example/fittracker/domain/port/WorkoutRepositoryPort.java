package com.example.fittracker.domain.port;

import com.example.fittracker.domain.model.Workout;

import java.util.List;
import java.util.Optional;

// I made this port because the domain require a Workout persistence
public interface WorkoutRepositoryPort {
    List<Workout> findAll();
    // I made an Optional class to avoid problems with null values
    // Using Optional because the user might not have this attribute
    Optional<Workout> findById(Long id);
    Optional<Workout> findByName(String name);
    Workout save(Workout w);
}
