package com.challenge.users.controllers;

import com.challenge.users.dtos.UserRequestDto;
import com.challenge.users.dtos.UserResponseDto;
import com.challenge.users.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping()
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = usersService.createUser(userRequestDto);

        log.info(userResponseDto.toString());

        return userResponseDto;
    }
}
