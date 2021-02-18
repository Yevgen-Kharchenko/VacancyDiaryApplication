package com.project.vacancy.exeption;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User is not found");
    }
}
