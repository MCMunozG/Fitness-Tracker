package com.example.fittracker.infrastructure.persistence.adapter;

import com.example.fittracker.domain.model.Exercise;
import com.example.fittracker.domain.model.Workout;
import com.example.fittracker.domain.model.WorkoutExercise;
import com.example.fittracker.domain.port.WorkoutRepositoryPort;
import com.example.fittracker.infrastructure.persistence.entity.ExerciseEntity;
import com.example.fittracker.infrastructure.persistence.entity.WorkoutEntity;
import com.example.fittracker.infrastructure.persistence.entity.WorkoutExerciseEntity;
import com.example.fittracker.infrastructure.persistence.repository.WorkoutJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
// This is my adapter class that connects the domain layer with the persistence layer
// for handling Workout-related operations using a JPA repository.
public class WorkoutRepositoryAdapter implements WorkoutRepositoryPort {
    private final WorkoutJpaRepository repo;
    private static Workout toDomain(WorkoutEntity e){
        var items = e.getItems().stream().map(it -> new WorkoutExercise(
            new Exercise(it.getExercise().getId(), it.getExercise().getName(), it.getExercise().getDescription()), it.getSets(), it.getReps()
        )).collect(Collectors.toList());
        return new Workout(e.getId(), e.getName(), e.getDescription(), items);
    }
    @Override public List<Workout> findAll(){
        return repo.findAll().stream().map(WorkoutRepositoryAdapter::toDomain).collect(Collectors.toList());
    }
    @Override public Optional<Workout> findById(Long id){
        return repo.findById(id).map(WorkoutRepositoryAdapter::toDomain);
    }
    @Override public Optional<Workout> findByName(String name){
        return repo.findByName(name).map(WorkoutRepositoryAdapter::toDomain);
    }
    // Since I used @Setter, I can now build my features
    @Override public Workout save(Workout w){
        WorkoutEntity we = new WorkoutEntity();
        we.setId(w.id());
        we.setName(w.name());
        we.setDescription(w.description());
        var items = w.items().stream().map(it -> {
            WorkoutExerciseEntity e = new WorkoutExerciseEntity();
            e.setWorkout(we);
            ExerciseEntity ex = new ExerciseEntity();
            ex.setId(it.exercise().id());
            ex.setName(it.exercise().name());
            ex.setDescription(it.exercise().description());
            e.setExercise(ex);
            e.setSets(it.sets());
            e.setReps(it.reps());
            return e;
        }).collect(Collectors.toList());
        we.setItems(items);
        return toDomain(repo.save(we));
    }
}

