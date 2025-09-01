package com.example.fittracker.domain.model;

import com.example.fittracker.domain.vo.Email;
import com.example.fittracker.domain.vo.PasswordHash;

// This is my User model
public record User(
        Long id,
        String firstName,
        String lastName,
        Email email,
        PasswordHash passwordHash,
        Role role
) {}
