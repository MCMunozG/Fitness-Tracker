package com.example.fittracker.domain.vo;

import java.util.regex.Pattern;

// This is my model for Email that is my Value Object
public record Email(String value) {
    private static final Pattern EMAIL = Pattern.compile("^[^@\n]+@[^@\n]+\\.[^@\n]+$");
    public Email {
        if (value == null || !EMAIL.matcher(value).matches()){
            throw new IllegalArgumentException("Invalid email: "+value);
        }
    }
    // Overriding toString to return the value directly instead of the full object
    @Override public String toString() {return value;}
}
