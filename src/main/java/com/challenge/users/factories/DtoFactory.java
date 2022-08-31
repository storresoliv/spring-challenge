package com.challenge.users.factories;

import com.challenge.users.dtos.ErrorDto;
import com.challenge.users.dtos.PhoneRequestDto;
import com.challenge.users.dtos.UserRequestDto;
import com.challenge.users.dtos.UserResponseDto;
import com.challenge.users.entities.UsersEntity;

import java.util.List;

public class DtoFactory {
    public static final ErrorDto createErrorDto(String message) {
        return ErrorDto
                .builder()
                .message(message)
                .build();
    }

    public static final UserRequestDto createUserRequestDto(String name, String email, String password, List<PhoneRequestDto> phones) {
        return UserRequestDto
                .builder()
                .name(name)
                .email(email)
                .password(password)
                .phones(phones)
                .build();
    }

    public static final UserResponseDto createUserResponseDto(UsersEntity user) {
        return UserResponseDto
                .builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.isActive())
                .build();
    }
}
