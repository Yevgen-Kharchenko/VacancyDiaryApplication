package com.project.vacancy.service;

import com.project.vacancy.exeption.UserNotFoundException;
import com.project.vacancy.model.ApplicationUser;

import java.util.List;

public interface UserService {
    List<ApplicationUser> getAllUsers();

    ApplicationUser createUser(ApplicationUser applicationUser);

    ApplicationUser findUser(String email, String password);

    ApplicationUser findById(Long id);

    void deleteById(Long id);

    ApplicationUser update(ApplicationUser applicationUser) throws UserNotFoundException;

    ApplicationUser findCurrentUser() throws UserNotFoundException;

    void deleteUser() throws UserNotFoundException;
}
