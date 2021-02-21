package com.project.vacancy.service;

import com.project.vacancy.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    User update(User user);

    User findCurrentUser();

    void deleteUser();
}
