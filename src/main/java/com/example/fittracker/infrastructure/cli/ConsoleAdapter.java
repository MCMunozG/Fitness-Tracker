package com.example.fittracker.infrastructure.cli;

import com.example.fittracker.application.service.AuthService;
import com.example.fittracker.application.service.WorkoutLogService;
import com.example.fittracker.application.service.WorkoutQueryService;
import com.example.fittracker.domain.model.User;
import com.example.fittracker.domain.model.Workout;
import com.example.fittracker.domain.model.WorkoutExercise;
import com.example.fittracker.domain.model.WorkoutLog;
import com.example.fittracker.domain.port.WorkoutRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@Component @Order(2) @RequiredArgsConstructor
// This is my client console
public class ConsoleAdapter implements CommandLineRunner {
    private final AuthService auth;
    private final WorkoutQueryService workoutsQ;
    private final WorkoutLogService logs;
    private final WorkoutRepositoryPort workoutRepo;

    @Override public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Personal Fitness Tracker (CLI) ===");
        User current = null;
        while (true){
            if (current == null){
                System.out.println("1) Register\n2) Log in\n0) Exit");
                String opt = sc.nextLine();
                try {
                    switch (opt){
                        case "1" -> current = register(sc);
                        case "2" -> current = login(sc);
                        case "0" -> { System.out.println("Bye!"); return; }
                        default -> System.out.println("Invalid Option");
                    }
                } catch (Exception e){ System.out.println("Error: " + e.getMessage()); }
            } else {
                System.out.println("\nWelcome, " + current.firstName() + "!");
                System.out.println("1) View Workouts\n2) Register Workout\n3) View History\n9) Log out\n0) Exit");
                String opt = sc.nextLine();
                switch (opt){
                    case "1" -> listWorkouts();
                    case "2" -> doLog(sc, current);
                    case "3" -> viewHistory(current);
                    case "9" -> current = null;
                    case "0" -> { System.out.println("Bye!"); return; }
                    default -> System.out.println("Invalid Option");
                }
            }
        }
    }
    private User register(Scanner sc){
        System.out.print("First Name: "); String first = sc.nextLine();
        System.out.print("Last Name: "); String last = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        return auth.register(first, last, email, pass);
    }
    private User login(Scanner sc){
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        return auth.login(email, pass);
    }
    private void listWorkouts(){
        System.out.println("\nAvailable Workouts:");
        workoutsQ.listAll().forEach(w -> {
            System.out.println("- " + w.name() + ": " + w.description());
            w.items().forEach(
                i -> System.out.println(" * " + i.exercise().name() + " (" + i.sets() + "x" + i.reps() + ")")
            );
        });
    }
    private void doLog(Scanner sc, User current){
        System.out.print("Workout Name: ");
        String name = sc.nextLine();
        Optional<Workout> w = workoutRepo.findByName(name);
        if (w.isEmpty()){ System.out.println("There is no such workout"); return; }
        Map<String,Integer> times = new LinkedHashMap<>();
        for (WorkoutExercise it : w.get().items()){
            System.out.print("Minutes in " + it.exercise().name() + ": ");
            try { times.put(it.exercise().name(), Integer.parseInt(sc.nextLine())); }
            catch (NumberFormatException nfe){ times.put(it.exercise().name(), 0); }
        }
        WorkoutLog saved = logs.log(current.id(), w.get(), times, LocalDate.now());
        System.out.println("Saved. Total: " + saved.totalMinutes() + " min, Calories: " + saved.calories());
    }
    private void viewHistory(User current){
        System.out.println("\nHistory:");
        logs.history(current.id()).forEach(l -> {
            String workoutName = workoutRepo.findById(l.workoutId()).map(Workout::name).orElse("?");
            System.out.println(l.date() + " - " + workoutName + " (" + l.totalMinutes() + " min, " + l.calories() + " cal)");
        });
    }
}

