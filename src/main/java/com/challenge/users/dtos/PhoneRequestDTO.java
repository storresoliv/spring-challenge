package com.challenge.users.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneRequestDTO {
    private String number;
    private String citycode;
    private String countrycode;
}
