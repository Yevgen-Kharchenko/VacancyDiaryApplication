package com.project.vacancy.service;

import com.project.vacancy.model.ApplicationUser;
import com.project.vacancy.repositiry.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository userRepository;

    /**
     * Finds User in the DB by Login and loads User in the session
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("User not found! " +
                "Email : " + email)) ;

        return new User(applicationUser.getEmail(),applicationUser.getPassword(), emptyList());

    }
}
