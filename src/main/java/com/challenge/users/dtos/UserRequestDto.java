package com.challenge.users.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
}
