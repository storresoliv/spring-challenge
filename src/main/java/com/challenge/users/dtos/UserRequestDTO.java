package com.challenge.users.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
public class UserRequestDTO {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z]).{5,}$")
    private String password;

    @NotNull
    private List<PhoneRequestDTO> phones;
}
