package com.project.vacancy.service;

import com.project.vacancy.exeption.ActionWithUserRuntimeException;
import com.project.vacancy.exeption.EntityNotExistRuntimeException;
import com.project.vacancy.exeption.InvalidDataRuntimeException;
import com.project.vacancy.model.User;
import com.project.vacancy.repositiry.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findUser(String email, String password) {
        if (Objects.isNull(email) || Objects.isNull(password)) {
            throw new InvalidDataRuntimeException("User data for finding is uncorrected");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = userRepository.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("User not found! " +
                "Email : " + email)) ;

        if (!user.getPassword().equals(encodedPassword)) {
            throw new ActionWithUserRuntimeException("User with this login and password is not exist");
        }

        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
