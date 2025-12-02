package com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper;

import com.UH.OtherLevel.domain.model.User;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.UserEntity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-01T23:30:51-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class UserEntityMapperImpl implements UserEntityMapper {

    @Override
    public User toDomain(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User user = new User();

        user.setId( entity.getId() );
        user.setUsername( entity.getUsername() );
        user.setEmail( entity.getEmail() );
        user.setPassword( entity.getPassword() );
        Set<String> set = entity.getRoles();
        if ( set != null ) {
            user.setRoles( new LinkedHashSet<String>( set ) );
        }

        return user;
    }

    @Override
    public UserEntity toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( user.getId() );
        userEntity.username( user.getUsername() );
        userEntity.email( user.getEmail() );
        userEntity.password( user.getPassword() );
        Set<String> set = user.getRoles();
        if ( set != null ) {
            userEntity.roles( new LinkedHashSet<String>( set ) );
        }

        return userEntity.build();
    }

    @Override
    public List<User> toDomainList(List<UserEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( entities.size() );
        for ( UserEntity userEntity : entities ) {
            list.add( toDomain( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserEntity> toEntityList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( users.size() );
        for ( User user : users ) {
            list.add( toEntity( user ) );
        }

        return list;
    }
}
