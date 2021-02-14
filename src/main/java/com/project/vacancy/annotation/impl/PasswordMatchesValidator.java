package com.project.vacancy.annotation.impl;


import com.project.vacancy.annotation.ValidPassword;
import com.project.vacancy.dto.LoginRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<ValidPassword, LoginRequest> {
private  final static int PASSWORD_LENGTH = 5;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(LoginRequest loginRequest, ConstraintValidatorContext constraintValidatorContext) {
        return loginRequest.getPassword() != null && loginRequest.getPassword().length() >= PASSWORD_LENGTH;

    }
}
