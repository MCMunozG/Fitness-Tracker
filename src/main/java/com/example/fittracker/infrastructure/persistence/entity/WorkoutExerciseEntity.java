package com.example.fittracker.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "workout_exercises")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
// This is my JPA entity for persisting exercise per workout data in the database
public class WorkoutExerciseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.EAGER)@JoinColumn(name = "workout_id",nullable = false)
    private WorkoutEntity workout;
    @ManyToOne(fetch = FetchType.EAGER)@JoinColumn(name = "exercise_id",nullable = false)
    private ExerciseEntity exercise;
    private Integer sets;
    private Integer reps;
}
