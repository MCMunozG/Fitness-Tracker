package com.example.fittracker.application.dto;

// This is my DTO for exposing user data outside the domain layer
public record UserDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    String role
) {}
