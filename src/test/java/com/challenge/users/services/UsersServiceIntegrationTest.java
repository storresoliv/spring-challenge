package com.challenge.users.services;

import com.challenge.users.dtos.UserRequestDto;
import com.challenge.users.dtos.UserResponseDto;
import com.challenge.users.factories.DtoFactory;
import com.challenge.users.repositories.PhonesRepository;
import com.challenge.users.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UsersServiceIntegrationTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    private PhonesService phonesService;

    private UsersService usersService;

    private Validator validator;

    @BeforeEach
    public void init() {
        this.phonesService = new PhonesService(phonesRepository);
        this.usersService = new UsersService(usersRepository, phonesService);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldCreated() {
        assertNotNull(this.usersService);
    }

    @Test
    public void shouldCreateUser() {
        UserRequestDto userRequestDto = DtoFactory.createUserRequestDto("testName", "test@test.com", "Aaa11", Collections.EMPTY_LIST);

        UserResponseDto userResponseDto = this.usersService.createUser(userRequestDto);

        assertNotNull(userResponseDto.getId());
    }
}
