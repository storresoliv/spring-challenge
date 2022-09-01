package com.challenge.users.mappers;

import com.challenge.users.dtos.PhoneRequestDTO;
import com.challenge.users.dtos.UserRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRequestDTOMapper {
    public UserRequestDTO createUserRequestDto(String name, String email, String password, List<PhoneRequestDTO> phones) {
        return UserRequestDTO
                .builder()
                .name(name)
                .email(email)
                .password(password)
                .phones(phones)
                .build();
    }
}
