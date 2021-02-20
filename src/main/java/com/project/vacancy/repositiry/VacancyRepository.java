package com.project.vacancy.repositiry;

import com.project.vacancy.model.ApplicationUser;
import com.project.vacancy.model.Vacancy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findAllByUser(ApplicationUser currentUser, Pageable pageable);
    List<Vacancy> findAllByUser(ApplicationUser currentUser);
}
