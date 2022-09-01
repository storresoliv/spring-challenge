package com.challenge.users.services;

import com.challenge.users.dtos.UserRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Service
@Validated
public class UsersValidationService {
    public void validate(@Valid UserRequestDTO user) throws ConstraintViolationException {}
}
