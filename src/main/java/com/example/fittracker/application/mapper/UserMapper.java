package com.example.fittracker.application.mapper;

import com.example.fittracker.application.dto.UserDTO;
import com.example.fittracker.domain.model.User;

// This is my mapper for converting User domain model into UserDTO
public class UserMapper {
    public static UserDTO toDTO(User u) {
        return new UserDTO(
                u.id(),
                u.firstName(),
                u.lastName(),
                u.email().toString(),
                u.role().name()
        );
    }
}
