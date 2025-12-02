package com.UH.OtherLevel.infrastructure.adapter.out.persistence.adapter;

import com.UH.OtherLevel.application.port.out.UserRepositoryPort;
import com.UH.OtherLevel.domain.model.User;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User save(User user) {
        log.info("DB_SAVE_USER username={}", user.getUsername());
        UserEntity entity = userEntityMapper.toEntity(user);
        UserEntity saved = userRepository.save(entity);
        log.info("DB_SAVE_USER_SUCCESS id={} username={}", saved.getId(), saved.getUsername());
        return userEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.info("DB_FIND_USER_BY_USERNAME username={}", username);
        return userRepository.findByUsername(username)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("DB_FIND_USER_BY_EMAIL email={}", email);
        return userRepository.findByEmail(email)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        log.info("DB_EXISTS_USER_BY_USERNAME username={}", username);
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("DB_EXISTS_USER_BY_EMAIL email={}", email);
        return userRepository.existsByEmail(email);
    }
}