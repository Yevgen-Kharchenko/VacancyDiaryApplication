package com.project.vacancy.controller;

import com.project.vacancy.exeption.UserNotFoundException;
import com.project.vacancy.model.ApplicationUser;
import com.project.vacancy.repositiry.ApplicationUserRepository;
import com.project.vacancy.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public List<ApplicationUser> getAll() {
        log.info("getAll users");
        return userService.getAllUsers();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody ApplicationUser applicationUser) {
        log.info("sign-up");
        userService.createUser(applicationUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<ApplicationUser> showUser() {
        log.info("get/profile");
        try {
            log.info("profile try");
            ApplicationUser userProfile = userService.findCurrentUser();
            log.info("current user "+ userProfile);
            return ResponseEntity.ok(userProfile);
        } catch(UserNotFoundException ex){
            log.info("profile catch");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found! ", ex);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<ApplicationUser> updateUser(@RequestBody ApplicationUser applicationUser) throws UserNotFoundException {
        log.info("Update profile");
        userService.update(applicationUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile")
    public ResponseEntity<Void> delete() throws UserNotFoundException {
        log.info("delete profile");
        userService.deleteUser();
        return ResponseEntity.ok().build();
    }

}
