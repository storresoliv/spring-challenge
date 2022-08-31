package com.challenge.users.factories;

import com.challenge.users.dtos.UserRequestDto;
import com.challenge.users.entities.UsersEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityFactoryTest {
    private Validator validator;

    @BeforeEach
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldCreateUserEntity() {
        UserRequestDto userRequestDto = DtoFactory.createUserRequestDto("testName", "test@test", "Aaa11", Collections.EMPTY_LIST);

        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(EntityFactory.createUsersEntity(userRequestDto, Collections.EMPTY_LIST));

        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldInvalidEmail() {
        UserRequestDto userRequestDto = DtoFactory.createUserRequestDto("testName", "testtest", "Aaa11", Collections.EMPTY_LIST);

        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(EntityFactory.createUsersEntity(userRequestDto, Collections.EMPTY_LIST));

        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldInvalidPassword() {
        UserRequestDto userRequestDto = DtoFactory.createUserRequestDto("testName", "test@test", "Aaaa1", Collections.EMPTY_LIST);

        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(EntityFactory.createUsersEntity(userRequestDto, Collections.EMPTY_LIST));

        assertFalse(violations.isEmpty());
    }
}
