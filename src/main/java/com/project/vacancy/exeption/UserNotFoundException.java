package com.project.vacancy.exeption;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("User is not found");
    }
}
