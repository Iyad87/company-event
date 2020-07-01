package com.mobiquity.codingevents.models.dto;

import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginFormDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid Username. Must be between 3 and 30 characters ")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20, message = "Invalid Password. Must be between 5 and 20 charachters")
    private String password;

}
