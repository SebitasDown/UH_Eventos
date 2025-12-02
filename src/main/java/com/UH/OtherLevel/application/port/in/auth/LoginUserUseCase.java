package com.UH.OtherLevel.application.port.in.auth;

import com.UH.OtherLevel.domain.model.User;

public interface LoginUserUseCase {
    User authenticate(String username, String rawPassword);
}