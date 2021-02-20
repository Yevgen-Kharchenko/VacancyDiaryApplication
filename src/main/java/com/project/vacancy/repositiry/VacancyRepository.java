package com.project.vacancy.repositiry;

import com.project.vacancy.model.ApplicationUser;
import com.project.vacancy.model.Vacancy;
import com.project.vacancy.model.enums.StatusVacancy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findAllByUser(ApplicationUser currentUser, Pageable pageable);
    List<Vacancy> findAllByUser(ApplicationUser currentUser);

    @Query("select vacancy from Vacancy vacancy where vacancy.user= :currentUser and vacancy.statusVacancy=:statusVacancy")
    List<Vacancy> findAllByUserAndStatusVacancy(StatusVacancy statusVacancy, ApplicationUser currentUser, Pageable pageable);

    @Query("select vacancy from Vacancy vacancy where vacancy.user= :currentUser and vacancy.nameCompany=:nameCompany")
    List<Vacancy> findAllByUserAndNameCompany(String nameCompany, ApplicationUser currentUser, Pageable pageable);
}
