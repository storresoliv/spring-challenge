package com.challenge.users.services;

import com.challenge.users.dtos.PhoneDto;
import com.challenge.users.dtos.UserRequestDto;
import com.challenge.users.dtos.UserResponseDto;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.entities.UsersEntity;
import com.challenge.users.repositories.PhonesRepository;
import com.challenge.users.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    public UserResponseDto createUser(UserRequestDto user) {
        List<PhoneDto> phonesDto = user.getPhones();
        UserResponseDto userResponseDto = null;

        List<PhonesEntity> phonesEntity = phonesDto
                .stream()
                .map(phone -> PhonesEntity
                        .builder()
                        .number(phone.getNumber())
                        .cityCode(phone.getCityCode())
                        .countryCode(phone.getCityCode())
                        .build())
                .collect(Collectors.toList());

        UsersEntity userEntity = UsersEntity
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(phonesEntity)
                .created(new Timestamp(System.currentTimeMillis()))
                .modified(new Timestamp(System.currentTimeMillis()))
                .lastLogin(null)
                .token("test")
                .isActive(true)
                .build();

        usersRepository.save(userEntity);

        userResponseDto = UserResponseDto
                .builder()
                .id(userEntity.getId())
                .created(userEntity.getCreated())
                .modified(userEntity.getModified())
                .lastLogin(null)
                .token("test")
                .isActive(true)
                .build();


        return userResponseDto;
    }
}
