package com.challenge.users.repositories;

import com.challenge.users.dtos.UserRequestDto;
import com.challenge.users.entities.UsersEntity;
import com.challenge.users.factories.DtoFactory;
import com.challenge.users.factories.EntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsersRepositoryTest {
    private Validator validator;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldSaveUser() {
        UserRequestDto userRequestDto = DtoFactory.createUserRequestDto("testName", "test@test", "Aaa11", Collections.EMPTY_LIST);
        UsersEntity usersEntity = EntityFactory.createUsersEntity(userRequestDto, Collections.EMPTY_LIST);

        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(usersRepository.save(usersEntity));

        assertTrue(violations.isEmpty());
        assertNotNull(entityManager.getId(usersEntity));
    }

    @Test
    public void shouldInvalidEmail() {
        UserRequestDto userRequestDto = DtoFactory.createUserRequestDto("testName", "testtest", "Aaa11", Collections.EMPTY_LIST);

        UsersEntity usersEntity = EntityFactory.createUsersEntity(userRequestDto, Collections.EMPTY_LIST);

        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(usersRepository.save(usersEntity));

        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldInvalidPassword() {
        UserRequestDto userRequestDto = DtoFactory.createUserRequestDto("testName", "test@test", "Aaaaaaa", Collections.EMPTY_LIST);

        UsersEntity usersEntity = EntityFactory.createUsersEntity(userRequestDto, Collections.EMPTY_LIST);

        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(usersRepository.save(usersEntity));

        assertFalse(violations.isEmpty());
    }
}
