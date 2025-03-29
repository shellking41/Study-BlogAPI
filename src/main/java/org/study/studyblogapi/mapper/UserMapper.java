package org.study.studyblogapi.mapper;

import org.study.studyblogapi.model.dto.UserResponse;
import org.study.studyblogapi.model.entity.User;

public class UserMapper {
    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
