package com.challenge.users.mappers;

import com.challenge.users.dtos.UserResponseDTO;
import com.challenge.users.entities.UsersEntity;
import com.challenge.users.exceptions.UserCreatedException;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDTOMapper {
    public UserResponseDTO createUserResponseDto(UsersEntity user) {
        try {
            return UserResponseDTO
                    .builder()
                    .id(user.getId())
                    .created(user.getCreated())
                    .modified(user.getModified())
                    .lastLogin(user.getLastLogin())
                    .token(user.getToken())
                    .isActive(user.isActive())
                    .build();
        } catch(NullPointerException ex) {
            throw new UserCreatedException();
        }
    }
}
