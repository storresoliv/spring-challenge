package com.challenge.users.services;

import com.challenge.users.dtos.UserRequestDTO;
import com.challenge.users.dtos.UserResponseDTO;
import com.challenge.users.entities.UsersEntity;
import com.challenge.users.mappers.UserRequestDTOMapper;
import com.challenge.users.mappers.UserResponseDTOMapper;
import com.challenge.users.mappers.UsersEntityMapper;
import com.challenge.users.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { UserRequestDTOMapper.class, UserResponseDTOMapper.class, UsersEntityMapper.class, UsersValidationService.class })
public class UsersServiceTest {
    private UsersService usersService;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private PhonesService phonesService;

    @Autowired
    private UserRequestDTOMapper userRequestDTOMapper;

    @Autowired
    private UserResponseDTOMapper userResponseDTOMapper;

    @Autowired
    private UsersEntityMapper usersEntityMapper;

    @BeforeEach
    public void init() {
        this.usersService = new UsersService(this.userResponseDTOMapper, this.usersEntityMapper, this.usersRepository, this.phonesService);
    }

    @Test
    public void shouldCreated() {
        assertNotNull(this.usersService);
    }

    @Test
    public void shouldCreateUser() {
        UserRequestDTO userRequestDto = this.userRequestDTOMapper.createUserRequestDto("testName", "test@test.com", "Aaa11", new ArrayList<>());
        UsersEntity user = this.usersEntityMapper.createUsersEntity(userRequestDto, new ArrayList<>());

        when(this.phonesService.createPhones(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.usersRepository.save(any(UsersEntity.class))).thenReturn(user);

        UserResponseDTO userResponseDto = this.usersService.createUser(userRequestDto);

        assertNotNull(userResponseDto.getCreated());
    }
}
