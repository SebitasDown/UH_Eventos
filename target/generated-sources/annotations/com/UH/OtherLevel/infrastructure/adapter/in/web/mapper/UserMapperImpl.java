package com.UH.OtherLevel.infrastructure.adapter.in.web.mapper;

import com.UH.OtherLevel.domain.model.User;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.auth.RegisterRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.auth.AuthResponse;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T22:04:17-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toModel(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( request.getUsername() );
        user.setEmail( request.getEmail() );
        user.setPassword( request.getPassword() );

        return user;
    }

    @Override
    public AuthResponse toAuthResponse(User user) {
        if ( user == null ) {
            return null;
        }

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();

        authResponse.id( user.getId() );
        authResponse.username( user.getUsername() );
        authResponse.email( user.getEmail() );
        Set<String> set = user.getRoles();
        if ( set != null ) {
            authResponse.roles( new LinkedHashSet<String>( set ) );
        }

        authResponse.type( "Bearer" );

        return authResponse.build();
    }
}
