package com.example.fittracker.application.service;

import com.example.fittracker.domain.model.Workout;
import com.example.fittracker.domain.model.WorkoutLog;
import com.example.fittracker.domain.port.WorkoutLogRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
// This is my application service for recording workouts with calculated stats and retrieving user logs
public class WorkoutLogService {
    private final WorkoutLogRepositoryPort logs;
    // Creates and saves a workout log with total minutes and calories calculated
    public WorkoutLog log(Long userId, Workout workout, Map<String,Integer> times, LocalDate date) {
        int total = times.values().stream().mapToInt(Integer::intValue).sum();
        int calories = Math.max(0, total*5);
        WorkoutLog wl = new WorkoutLog(null, userId, workout.id(), date, times, total, calories);
        return logs.save(wl);
    }
    // Retrieves all workout logs for the given user
    public List<WorkoutLog> history(Long userId) {return logs.findByUserId(userId);}
}
