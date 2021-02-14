//package com.project.vacancy.service;
//
//import com.project.vacancy.dto.AuthenticationResponse;
//import com.project.vacancy.dto.LoginRequest;
//import com.project.vacancy.dto.RegistrationRequest;
//import com.project.vacancy.repositiry.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//
//@Service
//@AllArgsConstructor
//public class AuthenticationService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final TokenService tokenService;
//
//
////    @Override
////    public User saveUser(RegistrationRequest registrationRequest) {
////        CustomUser user = mapper.convertRegistrationUser(registrationRequest);
////        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
////        userRepository.save(user);
////        return user;
////    }
//
//
//    public AuthenticationResponse loginUser(LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = tokenService.createToken(loginRequest);
//        return AuthenticationResponse.builder()
//                .authenticationToken(token)
//                .expiresAt(Instant.now().plusMillis(tokenService.getJwtExpirationInMills()))
//                .email(loginRequest.getEmail())
//                .build();
//    }
//
////    @Override
////    public User getCorrentUser() {
////        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        return userRepository.findByEmail(user.getUsername()).orElseThrow(() -> new AuthException("Something went wrong"));
////    }
//}
