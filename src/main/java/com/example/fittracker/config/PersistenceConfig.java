package com.example.fittracker.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {
    // I need use this class in the future if I want to create another adapter for one specific port
    // For example I have a Repository Port and I need a specific adapter Like MySQL or MongoDB
    // On this context you have both adapters
}
