package com.UH.OtherLevel.application.service.auth;

import com.UH.OtherLevel.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    private User user;
    private String token;
}

