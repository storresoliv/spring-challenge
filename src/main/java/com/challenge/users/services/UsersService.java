package com.challenge.users.services;

import com.challenge.users.dtos.UserRequestDTO;
import com.challenge.users.dtos.UserResponseDTO;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.entities.UsersEntity;
import com.challenge.users.exceptions.EmailDuplicatedException;
import com.challenge.users.exceptions.UserCreatedException;
import com.challenge.users.mappers.UserResponseDTOMapper;
import com.challenge.users.mappers.UsersEntityMapper;
import com.challenge.users.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsersService {
    private final UserResponseDTOMapper userResponseDTOMapper;
    private final UsersEntityMapper usersEntityMapper;
    private final UsersRepository usersRepository;
    private final PhonesService phonesService;

    public UsersService(@Autowired UserResponseDTOMapper userResponseDTOMapper,@Autowired UsersEntityMapper usersEntityMapper, @Autowired UsersRepository usersRepository, @Autowired PhonesService phonesService) {
        this.userResponseDTOMapper = userResponseDTOMapper;
        this.usersEntityMapper = usersEntityMapper;
        this.usersRepository = usersRepository;
        this.phonesService = phonesService;
    }

    public UserResponseDTO createUser(UserRequestDTO user) throws UserCreatedException, EmailDuplicatedException {
        UsersEntity userSaved;
        List<PhonesEntity> phonesEntities = this.phonesService.createPhones(user.getPhones());
        UsersEntity userEntity = this.usersEntityMapper.createUsersEntity(user, phonesEntities);

        try {
            userSaved = this.usersRepository.save(userEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new EmailDuplicatedException();
        }

        try {
            return this.userResponseDTOMapper.createUserResponseDto(userSaved);
        } catch (NullPointerException ex) {
            throw new UserCreatedException();
        }


    }
}
