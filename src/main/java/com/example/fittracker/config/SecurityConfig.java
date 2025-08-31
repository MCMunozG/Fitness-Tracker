package com.example.fittracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// This is my class with my config definition for manage my passwords encoding
@Configuration
public class SecurityConfig {
    // This is my method and return an object I'll use elsewhere
    // and I use Bean to return this object that I can inject (Dependency Injection)
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
