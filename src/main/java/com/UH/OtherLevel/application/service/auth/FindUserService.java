package com.UH.OtherLevel.application.service.auth;

import com.UH.OtherLevel.application.port.in.auth.FindUserUseCase;
import com.UH.OtherLevel.application.port.out.UserRepositoryPort;
import com.UH.OtherLevel.domain.model.User;

public class FindUserService implements FindUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User findByUsername(String username) {
        return userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    }
}