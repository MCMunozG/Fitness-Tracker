package com.example.fittracker.application.service;

import com.example.fittracker.domain.model.Role;
import com.example.fittracker.domain.model.User;
import com.example.fittracker.domain.port.UserRepositoryPort;
import com.example.fittracker.domain.vo.Email;
import com.example.fittracker.domain.vo.PasswordHash;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// This is my application service that is responsible for user authentication and registration
public class AuthService {

    // Domain requires persistence abstraction, then I use UserRepositoryPort
    private final UserRepositoryPort users;
    private final PasswordEncoder encoder;

    // This is my application service to register a new user
    public User register(String first, String last, String email, String rawPassword) {
        Email mail = new Email(email);
        users.findByEmail(mail).ifPresent(u -> {
            throw new IllegalArgumentException("Email already registered");
        });
        String hash = encoder.encode(rawPassword);
        return users.save(new User(null, first, last, mail, new PasswordHash(hash), Role.USER));
    }

    // This is my application service for login a user
    public User login(String email, String rawPassword) {
        return users.findByEmail(new Email(email))
                .filter(u -> encoder.matches(rawPassword, u.passwordHash().value()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Credentials"));
    }
}