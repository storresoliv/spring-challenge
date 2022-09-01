package com.challenge.users.mappers;

import com.challenge.users.dtos.UserRequestDTO;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.entities.UsersEntity;
import com.challenge.users.exceptions.EmailInvalidException;
import com.challenge.users.exceptions.InvalidParameterException;
import com.challenge.users.exceptions.PasswordInvalidException;
import com.challenge.users.services.UsersValidationService;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class UsersEntityMapper {
    private UsersValidationService usersValidationService;

    public UsersEntityMapper(@Autowired UsersValidationService usersValidationService) {
        this.usersValidationService = usersValidationService;
    }

    public UsersEntity createUsersEntity(UserRequestDTO user, List<PhonesEntity> phones) throws InvalidParameterException {
        try {
            this.usersValidationService.validate(user);

            return UsersEntity
                    .builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .phones(phones)
                    .created(new Timestamp(System.currentTimeMillis()))
                    .modified(new Timestamp(System.currentTimeMillis()))
                    .token(Jwts.builder().setIssuedAt(new Date()).compact())
                    .lastLogin(null)
                    .isActive(true)
                    .build();

        } catch (ConstraintViolationException ex) {
            if (ex.getMessage().contains("email")) {
                throw new EmailInvalidException(ex);
            }

            if (ex.getMessage().contains("password")) {
                throw new PasswordInvalidException(ex);
            }

            throw new InvalidParameterException(ex);
        }
    }
}
