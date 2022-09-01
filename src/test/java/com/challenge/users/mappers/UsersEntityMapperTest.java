package com.challenge.users.mappers;

import com.challenge.users.dtos.UserRequestDTO;
import com.challenge.users.exceptions.EmailInvalidException;
import com.challenge.users.exceptions.PasswordInvalidException;
import com.challenge.users.services.UsersValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest()
public class UsersEntityMapperTest {
    private UsersEntityMapper usersEntityMapper;

    @Autowired
    private UsersValidationService usersValidationService;

    @Autowired
    private UserRequestDTOMapper userRequestDTOMapper;

    @BeforeEach
    public void init() {
        usersEntityMapper = new UsersEntityMapper(usersValidationService);
    }

    @Test
    public void shouldInvalidEmail() {
        UserRequestDTO userRequestDto = this.userRequestDTOMapper.createUserRequestDto("testName", "testtest", "Aaa11", new ArrayList<>());

        EmailInvalidException ex = assertThrows(EmailInvalidException.class, () -> {
            this.usersEntityMapper.createUsersEntity(userRequestDto, new ArrayList<>());
        });

        assertEquals("email format is invalid", ex.getMessage());
    }

    @Test
    public void shouldInvalidPassword() {
        UserRequestDTO userRequestDto = this.userRequestDTOMapper.createUserRequestDto("testName", "test@test", "Aaaaaaa", new ArrayList<>());

        PasswordInvalidException ex = assertThrows(PasswordInvalidException.class, () -> {
            this.usersEntityMapper.createUsersEntity(userRequestDto, new ArrayList<>());
        });

        assertEquals("password parameter is invalid. Password must contain one upper char, at least two lower chars and at least two numbers", ex.getMessage());
    }
}
