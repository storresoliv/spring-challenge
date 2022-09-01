package com.challenge.users.repositories;

import com.challenge.users.dtos.UserRequestDTO;
import com.challenge.users.entities.UsersEntity;
import com.challenge.users.mappers.UserRequestDTOMapper;
import com.challenge.users.mappers.UsersEntityMapper;
import com.challenge.users.services.UsersValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest()
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@EnableJpaRepositories
public class UsersRepositoryTest {
    private UsersEntityMapper usersEntityMapper;
    private Validator validator;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsersValidationService usersValidationService;

    @Autowired
    private UserRequestDTOMapper userRequestDTOMapper;

    @BeforeEach
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

        usersEntityMapper = new UsersEntityMapper(usersValidationService);
    }

    @Test
    public void shouldSaveUser() {
        UserRequestDTO userRequestDto = this.userRequestDTOMapper.createUserRequestDto("testName", "test@test", "Aaa11", new ArrayList<>());
        UsersEntity usersEntity = this.usersEntityMapper.createUsersEntity(userRequestDto, new ArrayList<>());

        Set<ConstraintViolation<UsersEntity>> violations = this.validator.validate(this.usersRepository.save(usersEntity));

        assertTrue(violations.isEmpty());
        assertNotNull(this.entityManager.getId(usersEntity));
    }
}
