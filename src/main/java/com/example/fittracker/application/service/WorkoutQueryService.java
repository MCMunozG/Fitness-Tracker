package com.example.fittracker.application.service;

import com.example.fittracker.domain.model.Workout;
import com.example.fittracker.domain.port.WorkoutRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
// this is my application service that provides read access to Workouts through the repository port
public class WorkoutQueryService {
    private final WorkoutRepositoryPort workouts;
    // Retrieves all workouts from the infrastructure implementation of the port
    public List<Workout> listAll() {return workouts.findAll();}
}
