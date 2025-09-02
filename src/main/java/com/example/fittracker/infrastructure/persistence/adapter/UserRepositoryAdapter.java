package com.example.fittracker.infrastructure.persistence.adapter;

import com.example.fittracker.domain.model.Role;
import com.example.fittracker.domain.model.User;
import com.example.fittracker.domain.port.UserRepositoryPort;
import com.example.fittracker.domain.vo.Email;
import com.example.fittracker.domain.vo.PasswordHash;
import com.example.fittracker.infrastructure.persistence.entity.UserEntity;
import com.example.fittracker.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component @RequiredArgsConstructor
// This is my adapter class for handling User persistence using a JPA repository
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final UserJpaRepository repo;
    private static User toDomain(UserEntity e) {
        return new User(e.getId(), e.getFirstName(), e.getLastName(), new Email(e.getEmail()), new PasswordHash(e.getPasswordHash()), Role.valueOf(e.getRole()));
    }
    // Since I used @Builder, I can now build my features
    private static UserEntity toEntity(User u){
        return UserEntity.builder()
                .id(u.id())
                .firstName(u.firstName())
                .lastName(u.lastName())
                .email(u.email().toString())
                .passwordHash(u.passwordHash().value())
                .role(u.role().name())
                .build();
    }
    @Override public Optional<User> findByEmail(Email email){
        return repo.findByEmail(email.toString()).map(UserRepositoryAdapter::toDomain);
    }
    @Override public Optional<User> findById(Long id){
        return repo.findById(id).map(UserRepositoryAdapter::toDomain);
    }
    @Override public User save(User user){
        UserEntity saved = repo.save(toEntity(user));
        return toDomain(saved);
    }
    @Override
    public Long countAdmins(){ return repo.countByRole(Role.ADMIN.name()); }
}
