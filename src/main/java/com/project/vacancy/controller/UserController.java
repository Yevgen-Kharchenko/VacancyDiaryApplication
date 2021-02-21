package com.project.vacancy.controller;

import com.project.vacancy.exeption.UserNotFoundException;
import com.project.vacancy.model.User;
import com.project.vacancy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        log.info("getAll users");
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody User applicationUser) {
        User user = userService.createUser(applicationUser);
        log.info("sign-up user with id {}", user.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<User> showUser() {
        log.info("get/profile");
        try {
            log.info("profile try");
            User userProfile = userService.findCurrentUser();
            log.info("current user " + userProfile);
            return ResponseEntity.ok(userProfile);
        } catch (UserNotFoundException ex) {
            log.info("profile catch");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found! ", ex);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        log.info("Update profile");
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile")
    public ResponseEntity delete() {
        log.info("delete profile");
        userService.deleteUser();
        return ResponseEntity.ok().build();
    }

}
