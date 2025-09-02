package com.example.fittracker.infrastructure.persistence.adapter;

import com.example.fittracker.domain.model.*;
import com.example.fittracker.domain.port.ExerciseRepositoryPort;
import com.example.fittracker.domain.port.UserRepositoryPort;
import com.example.fittracker.domain.port.WorkoutRepositoryPort;
import com.example.fittracker.domain.vo.Email;
import com.example.fittracker.domain.vo.PasswordHash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @Order(1)
// This is my initializer that validates and loads default data if required
public class DataInitializer implements CommandLineRunner {
    private final boolean seedAdmin;
    private final boolean seedDefault;
    private final UserRepositoryPort users;
    private final ExerciseRepositoryPort exercises;
    private final WorkoutRepositoryPort workouts;
    private final PasswordEncoder encoder;
    public DataInitializer(
        // Load configuration values to control whether admin and default data should be seeded at startup
        @Value("${app.seed-admin:true}") boolean seedAdmin,
        @Value("${app.seed-default-data:true}") boolean seedDefault,
        UserRepositoryPort users,
        ExerciseRepositoryPort exercises,
        WorkoutRepositoryPort workouts,
        PasswordEncoder encoder
    ) {
        this.seedAdmin = seedAdmin;
        this.seedDefault = seedDefault;
        this.users = users;
        this.exercises = exercises;
        this.workouts = workouts;
        this.encoder = encoder;
    }
    @Override public void run(String... args){
        // This is my default user generation
        if (seedAdmin && users.countAdmins() == 0){
            var admin = new User(
                null,
                "Default",
                "Admin",
                new Email("admin@example.com"),
                new PasswordHash(encoder.encode("admin123")),
                Role.ADMIN
            );
            users.save(admin);
        }
        // This is my default exercise and workout generation
        if (seedDefault && workouts.findAll().isEmpty()){
            // Exercises
            Exercise run = exercises.findByName("Running").orElseGet(() -> exercises.save(new Exercise(
                null,
                "Running",
                "Outdoor running"
            )));
            Exercise push = exercises.findByName("Push-Ups").orElseGet(() -> exercises.save(new Exercise(
                null,
                "Push-Ups",
                "Upper body"
            )));
            Exercise squat = exercises.findByName("Squats").orElseGet(() -> exercises.save(new Exercise(
                null,
                "Squats",
                "Lower body"
            )));
            Exercise cycle = exercises.findByName("Cycling").orElseGet(() -> exercises.save(new Exercise(
                null,
                "Cycling",
                "Bike cardio"
            )));
            // Workouts
            List<WorkoutExercise> cardioItems = List.of(
                new WorkoutExercise(run, 1, 1),
                new WorkoutExercise(cycle, 1, 1)
            );
            workouts.save(new Workout(null, "Cardio Blast", "Varied cardio", cardioItems));
            List<WorkoutExercise> strengthItems = List.of(
                new WorkoutExercise(push, 4, 12),
                new WorkoutExercise(squat, 4, 12)
            );
            workouts.save(new Workout(null, "Strength Builder", "Basic strength", strengthItems));
        }
    }
}
