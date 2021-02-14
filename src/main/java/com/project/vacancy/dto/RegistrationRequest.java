package com.project.vacancy.dto;

import com.project.vacancy.annotation.ValidPassword;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
public class RegistrationRequest {

    @Size(max = 30)
    private String name;

    @Email
    private String email;

    @ValidPassword
    private String password;

}
