package com.UH.OtherLevel.application.service.auth;

import com.UH.OtherLevel.application.port.in.auth.LoginUserUseCase;
import com.UH.OtherLevel.application.port.out.JwtTokenPort;
import com.UH.OtherLevel.application.port.out.PasswordPort;
import com.UH.OtherLevel.application.port.out.UserRepositoryPort;
import com.UH.OtherLevel.domain.model.User;

public class LoginUserService implements LoginUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final JwtTokenPort jwtTokenPort;
    private final PasswordPort passwordPort;

    public LoginUserService(
            UserRepositoryPort userRepositoryPort,
            JwtTokenPort jwtTokenPort,
            PasswordPort passwordPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.jwtTokenPort = jwtTokenPort;
        this.passwordPort = passwordPort;
    }

    @Override
    public LoginResult authenticate(String username, String rawPassword) {
        User user = userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!passwordPort.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        String token = jwtTokenPort.generateToken(user);

        return new LoginResult(user, token);
    }
}