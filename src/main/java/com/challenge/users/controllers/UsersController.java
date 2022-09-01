package com.challenge.users.controllers;

import com.challenge.users.dtos.UserRequestDTO;
import com.challenge.users.dtos.UserResponseDTO;
import com.challenge.users.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(userRequestDto));
    }
}
