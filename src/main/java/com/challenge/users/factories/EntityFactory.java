package com.challenge.users.factories;

import com.challenge.users.dtos.PhoneRequestDto;
import com.challenge.users.dtos.UserRequestDto;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.entities.UsersEntity;
import io.jsonwebtoken.Jwts;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class EntityFactory {
    public static final UsersEntity createUsersEntity(UserRequestDto user, List<PhonesEntity> phones) {
        return UsersEntity
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .phones(phones)
                .password(user.getPassword())
                .created(new Timestamp(System.currentTimeMillis()))
                .modified(new Timestamp(System.currentTimeMillis()))
                .token(Jwts.builder().setIssuedAt(new Date()).compact())
                .lastLogin(null)
                .isActive(true)
                .build();
    }

    public static final PhonesEntity createPhonesEntity(PhoneRequestDto phone) {
        return PhonesEntity
                .builder()
                .number(phone.getNumber())
                .cityCode(phone.getCitycode())
                .countryCode(phone.getCitycode())
                .build();
    }
}
