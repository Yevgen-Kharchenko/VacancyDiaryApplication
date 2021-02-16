package com.project.vacancy.service;

import com.project.vacancy.exeption.ActionWithUserRuntimeException;
import com.project.vacancy.exeption.InvalidDataRuntimeException;
import com.project.vacancy.model.ApplicationUser;
import com.project.vacancy.repositiry.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUser findUser(String email, String password) {
        if (Objects.isNull(email) || Objects.isNull(password)) {
            throw new InvalidDataRuntimeException("User data for finding is uncorrected");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        ApplicationUser applicationUser = applicationUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found! " +
                "Email : " + email));

        if (!applicationUser.getPassword().equals(encodedPassword)) {
            throw new ActionWithUserRuntimeException("User with this login and password is not exist");
        }

        return applicationUser;
    }

    public List<ApplicationUser> getAllUsers() {
        return applicationUserRepository.findAll();
    }

    public ApplicationUser findById(Long id) {
        return applicationUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found! "));
    }

    public void deleteById(Long id) {
        applicationUserRepository.deleteById(id);
    }

    public ApplicationUser saveUser(ApplicationUser applicationUser) {
        ApplicationUser userExist = findUser(applicationUser.getEmail(), applicationUser.getPassword());
        if (Objects.nonNull(userExist)) {
            throw new ActionWithUserRuntimeException("User exist. Can you login.");
        }
        return applicationUserRepository.save(applicationUser);
    }

    public ApplicationUser edit(ApplicationUser applicationUser) {
        ApplicationUser editUser = ApplicationUser.builder()
                .name(applicationUser.getName())
                .email(applicationUser.getEmail())
                .password(applicationUser.getPassword())
                .build();
        return applicationUserRepository.save(editUser);
    }
}
