package com.UH.OtherLevel.application.port.in.auth;

import com.UH.OtherLevel.application.service.auth.LoginResult;

public interface LoginUserUseCase {
    LoginResult authenticate(String username, String rawPassword);
}