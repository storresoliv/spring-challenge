package com.challenge.users.services;

import com.challenge.users.dtos.PhoneRequestDto;
import com.challenge.users.dtos.UserRequestDto;
import com.challenge.users.dtos.UserResponseDto;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.factories.DtoFactory;
import com.challenge.users.repositories.PhonesRepository;
import com.challenge.users.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ServicesIntegrationTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private TestEntityManager entityManager;

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

    @Test
    public void shouldSavePhones() {
        List<PhoneRequestDto> phonesRequestDto = new ArrayList<>();

        PhoneRequestDto phoneOneRequestDto = DtoFactory.createPhoneRequestDto("1", "1", "1");
        PhoneRequestDto phoneTwoRequestDto = DtoFactory.createPhoneRequestDto("1", "1", "1");
        PhoneRequestDto phoneThreeRequestDto = DtoFactory.createPhoneRequestDto("1", "1", "1");

        phonesRequestDto.add(phoneOneRequestDto);
        phonesRequestDto.add(phoneTwoRequestDto);
        phonesRequestDto.add(phoneThreeRequestDto);

        List<PhonesEntity> phonesEntities = this.phonesService.createPhones(phonesRequestDto);

        assertNotNull(entityManager.getId(phonesEntities.get(0)));
        assertNotNull(entityManager.getId(phonesEntities.get(1)));
        assertNotNull(entityManager.getId(phonesEntities.get(2)));

    }
}
