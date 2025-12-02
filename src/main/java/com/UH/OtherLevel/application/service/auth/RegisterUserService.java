package com.UH.OtherLevel.application.service.auth;

import com.UH.OtherLevel.application.port.in.auth.RegisterUserUseCase;
import com.UH.OtherLevel.application.port.out.UserRepositoryPort;
import com.UH.OtherLevel.domain.model.User;

import java.util.HashSet;
import java.util.Set;

public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public RegisterUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User register(User user, String rawPassword) {

        if (userRepositoryPort.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }


        if (userRepositoryPort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }


        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        user.setRoles(roles);


        return userRepositoryPort.save(user);
    }
}
