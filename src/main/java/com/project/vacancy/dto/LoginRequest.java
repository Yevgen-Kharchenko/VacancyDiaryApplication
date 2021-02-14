package com.project.vacancy.dto;

import com.project.vacancy.annotation.ValidPassword;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class LoginRequest {
    @Email
    String email;
    @ValidPassword
    String password;
}
