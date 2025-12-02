package com.UH.OtherLevel.application.port.in.auth;

import com.UH.OtherLevel.domain.model.User;

public interface RegisterUserUseCase {
    User register(User user, String rawPassword);
}
