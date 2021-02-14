package com.project.vacancy.controller;

//import com.project.vacancy.dto.AuthenticationResponse;
//import com.project.vacancy.dto.LoginRequest;
//import com.project.vacancy.service.AuthenticationService;
import com.project.vacancy.dto.LoginRequest;
import com.project.vacancy.model.User;
import com.project.vacancy.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController

@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping ("/getAll")
    public List<User> getAll() {
        log.info("getAll Controller");
        return userService.getAllUsers();
    }
//    @GetMapping("/getAll")
//    public User login(@RequestBody User user) {
//        return userService.findUser(user.getEmail(), user.getPassword());
//    }
//    private final AuthenticationService authenticationService;


//    @PostMapping("/login")
//    public AuthenticationResponse login(@RequestBody @Valid LoginRequest loginRequest){
//        return authenticationService.loginUser(loginRequest);
//    }

}
