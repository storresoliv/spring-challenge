package com.challenge.users.dtos;

import lombok.Data;

@Data
public class PhoneRequestDto {
    private String number;
    private String citycode;
    private String countrycode;
}
