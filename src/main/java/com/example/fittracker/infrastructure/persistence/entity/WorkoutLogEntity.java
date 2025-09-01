package com.example.fittracker.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Entity @Table(name = "workout_logs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
// This is my JPA entity for persisting log workout exercises data in the database
public class WorkoutLogEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false) private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "workout_id", nullable = false) private WorkoutEntity workout;
    @Column(nullable = false) private LocalDate date;
    private Integer totalMinutes;
    private Integer calories;
    @ElementCollection
    @CollectionTable(name = "workout_log_times", joinColumns = @JoinColumn(name = "workout_log_id"))
    @MapKeyColumn(name = "exercise_name") @Column(name = "minutes") private Map<String, Integer> timesPerExerciseMinutes;
}
