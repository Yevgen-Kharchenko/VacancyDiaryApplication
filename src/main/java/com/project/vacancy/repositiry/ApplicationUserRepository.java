package com.project.vacancy.repositiry;

import com.project.vacancy.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByEmail(String email);

    boolean existsByEmail(String email);
}