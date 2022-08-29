package com.challenge.users.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDto {
    private String number;
    private String cityCode;
    private String countryCode;
}
