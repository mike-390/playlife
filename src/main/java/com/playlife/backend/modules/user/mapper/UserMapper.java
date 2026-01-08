package com.playlife.backend.modules.user.mapper;

import com.playlife.backend.modules.user.Entity.User;
import com.playlife.backend.modules.user.dto.RegisterRequest;
import com.playlife.backend.modules.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(RegisterRequest request);

    UserResponse toResponse(User user);
}
