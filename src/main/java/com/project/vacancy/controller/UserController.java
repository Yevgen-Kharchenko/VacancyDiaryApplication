package com.project.vacancy.controller;

import com.project.vacancy.model.ApplicationUser;
import com.project.vacancy.repositiry.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public List<ApplicationUser> getAll() {
        log.info("getAll users");
        return applicationUserRepository.findAll();
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser applicationUser) {
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        applicationUserRepository.save(applicationUser);
    }

    @GetMapping("/{id}")
    public ApplicationUser showUser(@PathVariable Long id) {
        return applicationUserRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found! "));
    }

    @PutMapping("/{id}")
    public void editUser(@PathVariable long id, @RequestBody ApplicationUser applicationUser) {
        ApplicationUser existingUser = applicationUserRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found! "));
        Assert.notNull(existingUser, "User not found");
        existingUser.setName(applicationUser.getName());
        existingUser.setEmail(applicationUser.getEmail());
        existingUser.setPassword(applicationUser.getPassword());
        applicationUserRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ApplicationUser userToDel = applicationUserRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found! "));
        applicationUserRepository.delete(userToDel);
    }

}
