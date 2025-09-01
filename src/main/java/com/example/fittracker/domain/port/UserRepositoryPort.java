package com.example.fittracker.domain.port;

import com.example.fittracker.domain.model.User;
import com.example.fittracker.domain.vo.Email;

import java.util.Optional;

// I made this port because the domain require a User persistence
public interface UserRepositoryPort {
    // I made an Optional class to avoid problems with null values
    // Using Optional because the user might not have this attribute
    Optional<User> findByEmail(Email email);
    Optional<User> findById(Long id);
    User save(User user);
    Long countAdmins();
}
