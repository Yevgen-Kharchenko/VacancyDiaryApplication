//package com.project.vacancy.service;
//
//import com.project.vacancy.model.User;
//import com.project.vacancy.repository.UserRepo;
//import com.project.vacancy.service.security.CustomUserDetails;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.servlet.http.HttpSession;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserRepo userRepo;
//    private final HttpSession session;
//
//    public UserDetailsServiceImpl(UserRepo userRepo, HttpSession session) {
//        this.userRepo = userRepo;
//        this.session = session;
//    }
//
//    /**
//     * Finds User in the DB by Login and loads User in the session
//     *
//     * @param login
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = userRepo.findByLogin(login);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found! Username : " + login);
//        }
//        session.setAttribute("user", user);
//        return new CustomUserDetails(user);
//    }
//}
