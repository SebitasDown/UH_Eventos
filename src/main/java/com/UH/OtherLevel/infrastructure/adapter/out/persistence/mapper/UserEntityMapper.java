package com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper;

import com.UH.OtherLevel.domain.model.User;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User user);

    List<User> toDomainList(List<UserEntity> entities);

    List<UserEntity> toEntityList(List<User> users);
}