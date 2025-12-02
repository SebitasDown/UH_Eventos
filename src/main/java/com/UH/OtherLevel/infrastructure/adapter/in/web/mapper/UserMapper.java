package com.UH.OtherLevel.infrastructure.adapter.in.web.mapper;

import com.UH.OtherLevel.domain.model.User;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.auth.RegisterRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.auth.AuthResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toModel(RegisterRequest request);

    @Mapping(target = "token", ignore = true)
    @Mapping(target = "type", constant = "Bearer")
    AuthResponse toAuthResponse(User user);
}
