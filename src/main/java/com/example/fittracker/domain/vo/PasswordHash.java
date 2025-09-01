package com.example.fittracker.domain.vo;

// This is my model for Password that is my Value Object
public record PasswordHash(String value) {
    public PasswordHash {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("PasswordHash cannot be empty");
        }
    }
    // Overriding toString to return the value directly instead of the full object
    @Override public String toString(){ return value; }
}
