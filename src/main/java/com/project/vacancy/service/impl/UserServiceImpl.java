package com.project.vacancy.service.impl;

import com.project.vacancy.exeption.ActionWithUserRuntimeException;
import com.project.vacancy.exeption.InvalidDataRuntimeException;
import com.project.vacancy.exeption.UserNotFoundException;
import com.project.vacancy.model.ApplicationUser;
import com.project.vacancy.model.Vacancy;
import com.project.vacancy.repositiry.ApplicationUserRepository;
import com.project.vacancy.repositiry.VacancyRepository;
import com.project.vacancy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final VacancyRepository vacancyRepository;

    @Override
    public List<ApplicationUser> getAllUsers() {
        return applicationUserRepository.findAll();
    }

    @Override
    public ApplicationUser createUser(ApplicationUser applicationUser) {

        if (applicationUserRepository.existsByEmail(applicationUser.getEmail())) {
            throw new ActionWithUserRuntimeException("User exist.");
        }
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        return applicationUserRepository.save(applicationUser);
    }


    @Override
    public ApplicationUser findUser(String email, String password) {
        if (Objects.isNull(email) || Objects.isNull(password)) {
            throw new InvalidDataRuntimeException("User data for finding is uncorrected");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        ApplicationUser applicationUser = applicationUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found! " +
                        "Email : " + email));

        if (!applicationUser.getPassword().equals(encodedPassword)) {
            throw new ActionWithUserRuntimeException("User with this login and password is not exist");
        }

        return applicationUser;
    }

    @Override
    public ApplicationUser findById(Long id) {
        return applicationUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found! "));
    }

    @Override
    public void deleteById(Long id) {
        applicationUserRepository.deleteById(id);
    }

    @Override
    public ApplicationUser update(ApplicationUser applicationUser) throws UserNotFoundException {
        ApplicationUser updatedUser = findCurrentUser();
        updatedUser.setName(applicationUser.getName());
        updatedUser.setPassword(applicationUser.getPassword());
        return applicationUserRepository.save(updatedUser);
    }

    @Override
    public ApplicationUser findCurrentUser() throws UserNotFoundException {
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

        return applicationUserRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteUser() throws UserNotFoundException {
        ApplicationUser deletedUser = findCurrentUser();
        List<Vacancy> usersVacancies = vacancyRepository.findAllByUser(deletedUser);
        if (!usersVacancies.isEmpty()) {
            for (Vacancy vacancy : usersVacancies) {
                vacancyRepository.delete(vacancy);
            }
        }
        applicationUserRepository.delete(deletedUser);
    }
}
