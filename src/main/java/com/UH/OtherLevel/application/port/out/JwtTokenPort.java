package com.UH.OtherLevel.application.port.out;

import com.UH.OtherLevel.domain.model.User;

public interface JwtTokenPort {
    String generateToken(User user);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}

