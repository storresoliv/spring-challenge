package com.challenge.users.mappers;

import com.challenge.users.dtos.PhoneRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PhoneRequestDTOMapper {
    public PhoneRequestDTO createPhoneRequestDto(String number, String cityCode, String countryCode) {
        return PhoneRequestDTO
                .builder()
                .number(number)
                .citycode(cityCode)
                .countrycode(countryCode)
                .build();
    }
}
