package com.example.fittracker.infrastructure.persistence.adapter;

import com.example.fittracker.domain.model.Exercise;
import com.example.fittracker.domain.port.ExerciseRepositoryPort;
import com.example.fittracker.infrastructure.persistence.entity.ExerciseEntity;
import com.example.fittracker.infrastructure.persistence.repository.ExerciseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
// This is my adapter class for managing Exercise persistence using a JPA repository
public class ExerciseRepositoryAdapter implements ExerciseRepositoryPort {
    private final ExerciseJpaRepository repo;
    private static Exercise toDomain(ExerciseEntity e){
        return new Exercise(e.getId(), e.getName(), e.getDescription());
    }
    // Since I used @Builder, I can now build my features
    private static ExerciseEntity toEntity(Exercise e){
        return ExerciseEntity.builder().id(e.id()).name(e.name()).description(e.description()).build();
    }
    @Override public List<Exercise> findAll(){
        return repo.findAll().stream().map(ExerciseRepositoryAdapter::toDomain).collect(Collectors.toList());
    }
    @Override public Optional<Exercise> findByName(String name){
        return repo.findByName(name).map(ExerciseRepositoryAdapter::toDomain);
    }
    @Override public Exercise save(Exercise e){
        return toDomain(repo.save(toEntity(e)));
    }
}

