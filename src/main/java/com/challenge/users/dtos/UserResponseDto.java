package com.challenge.users.dtos;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class UserResponseDto {
    private UUID id;
    private Timestamp created;
    private Timestamp modified;
    private Timestamp lastLogin;
    private String token;
    private boolean isActive;
}
