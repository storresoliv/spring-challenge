package com.challenge.users.services;

import com.challenge.users.dtos.UserRequestDto;
import com.challenge.users.dtos.UserResponseDto;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.entities.UsersEntity;
import com.challenge.users.factories.DtoFactory;
import com.challenge.users.factories.EntityFactory;
import com.challenge.users.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsersService {

    private UsersRepository usersRepository;

    private PhonesService phonesService;

    public UsersService(@Autowired UsersRepository usersRepository, @Autowired PhonesService phonesService) {
        this.usersRepository = usersRepository;
        this.phonesService = phonesService;
    }

    public UserResponseDto createUser(UserRequestDto user) {
        List<PhonesEntity> phonesRequestDto = phonesService.createPhones(user.getPhones());

        UsersEntity userEntity = EntityFactory.createUsersEntity(user, phonesRequestDto);

        UsersEntity userSaved = usersRepository.save(userEntity);

        return DtoFactory.createUserResponseDto(userSaved);
    }
}
