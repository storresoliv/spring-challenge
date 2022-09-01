package com.challenge.users.mappers;

import com.challenge.users.dtos.PhoneRequestDTO;
import com.challenge.users.entities.PhonesEntity;
import org.springframework.stereotype.Component;

@Component
public class PhonesEntityMapper {
    public PhonesEntity createPhonesEntity(PhoneRequestDTO phone) {
        return PhonesEntity
                .builder()
                .number(phone.getNumber())
                .cityCode(phone.getCitycode())
                .countryCode(phone.getCitycode())
                .build();
    }
}
