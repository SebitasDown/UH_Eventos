package com.UH.OtherLevel.application.port.in.auth;

import com.UH.OtherLevel.domain.model.User;

public interface FindUserUseCase {
    User findByUsername(String username);
}