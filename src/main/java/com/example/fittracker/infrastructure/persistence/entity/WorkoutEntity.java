package com.example.fittracker.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "workouts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
// This is my JPA entity for persisting workout data in the database
public class WorkoutEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    private String description;
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default private List<WorkoutExerciseEntity> items = new ArrayList<>();
}
