package com.challenge.users.mappers;

import com.challenge.users.dtos.ErrorDTO;
import org.springframework.stereotype.Component;

@Component
public class ErrorDTOMapper {
    public ErrorDTO createErrorDto(String message) {
        return ErrorDTO
                .builder()
                .message(message)
                .build();
    }
}
