package com.UH.OtherLevel.application.service.auth;

import com.UH.OtherLevel.application.port.in.auth.LoginUserUseCase;
import com.UH.OtherLevel.application.port.out.UserRepositoryPort;
import com.UH.OtherLevel.domain.model.User;

public class LoginUserService implements LoginUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public LoginUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User authenticate(String username, String rawPassword) {

        return userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}