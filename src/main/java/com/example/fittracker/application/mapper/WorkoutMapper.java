package com.example.fittracker.application.mapper;

import com.example.fittracker.application.dto.WorkoutDTO;
import com.example.fittracker.application.dto.WorkoutExerciseDTO;
import com.example.fittracker.domain.model.Workout;

import java.util.stream.Collectors;

// This is my mapper for converting Workout domain model into WorkoutDTO
public class WorkoutMapper {
    public static WorkoutDTO toDTO(Workout w) {
        // Maps each workout item (exercise, sets, reps) into WorkoutExerciseDTOs for the WorkoutDTO
        // Using var for type inference, compiler knows this is List<WorkoutExerciseDTO>
        var items = w.items().stream()
            .map(i -> new WorkoutExerciseDTO(i.exercise().name(), i.sets(), i.reps()))
            .collect(Collectors.toList());
        return new WorkoutDTO(
            w.id(),
            w.name(),
            w.description(),
            items
        );
    }
}
