package com.project.vacancy.service.impl;

import com.project.vacancy.exeption.ActionWithUserRuntimeException;
import com.project.vacancy.exeption.UserNotFoundException;
import com.project.vacancy.model.User;
import com.project.vacancy.repositiry.UserRepository;
import com.project.vacancy.repositiry.VacancyRepository;
import com.project.vacancy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final VacancyRepository vacancyRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ActionWithUserRuntimeException("User exist.");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User updatedUser = findCurrentUser();
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }

    @Override
    public User findCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication auth =" + auth);
        if (null == auth) {
            throw new UserNotFoundException();
        }

        Object obj = auth.getPrincipal();
        String username = "";

        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }

        return userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteUser() {
        User deletedUser = findCurrentUser();
        vacancyRepository.deleteAll(vacancyRepository.findAllByUser(deletedUser));
        userRepository.delete(deletedUser);
    }
}
