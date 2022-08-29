package com.challenge.users.dtos;

import lombok.Builder;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
public class UserResponseDto {
    private UUID id;
    private Timestamp created;
    private Timestamp modified;
    private Timestamp lastLogin;
    private String token;
    private boolean isActive;
}
