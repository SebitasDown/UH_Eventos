package com.UH.OtherLevel.infrastructure.adapter.in.web.controller;

import com.UH.OtherLevel.application.port.in.auth.FindUserUseCase;
import com.UH.OtherLevel.application.port.in.auth.LoginUserUseCase;
import com.UH.OtherLevel.application.port.in.auth.RegisterUserUseCase;
import com.UH.OtherLevel.application.service.auth.LoginResult;
import com.UH.OtherLevel.domain.model.User;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.auth.LoginRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.auth.RegisterRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.auth.AuthResponse;
import com.UH.OtherLevel.infrastructure.adapter.in.web.mapper.UserMapper;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.adapter.config.TransactionalUseCaseExecutor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final UserMapper userMapper;
    private final TransactionalUseCaseExecutor transactionalExecutor;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        log.info("AUTH_REGISTER_REQUEST username={}", request.getUsername());

        User user = userMapper.toModel(request);

        User savedUser = transactionalExecutor.executeInTransaction(() ->
                registerUserUseCase.register(user, request.getPassword())
        );

        log.info("AUTH_REGISTER_SUCCESS username={} id={}", savedUser.getUsername(), savedUser.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("AUTH_LOGIN_REQUEST username={}", request.getUsername());

        LoginResult loginResult = transactionalExecutor.executeInTransaction(() ->
                loginUserUseCase.authenticate(request.getUsername(), request.getPassword())
        );

        log.info("AUTH_LOGIN_SUCCESS username={} id={}", loginResult.getUser().getUsername(), loginResult.getUser().getId());

        AuthResponse response = userMapper.toAuthResponse(loginResult.getUser());
        response.setToken(loginResult.getToken());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        log.info("AUTH_GET_ME_REQUEST username={}", username);

        User user = transactionalExecutor.executeReadOnly(() ->
                findUserUseCase.findByUsername(username)
        );

        AuthResponse response = userMapper.toAuthResponse(user);

        return ResponseEntity.ok(response);
    }
}
