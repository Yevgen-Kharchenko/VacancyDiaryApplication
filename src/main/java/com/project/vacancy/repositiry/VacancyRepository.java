package com.project.vacancy.repositiry;

import com.project.vacancy.model.User;
import com.project.vacancy.model.Vacancy;
import com.project.vacancy.model.enums.StatusVacancy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findAllByUser(User currentUser, Pageable pageable);

    List<Vacancy> findAllByUser(User currentUser);

    @Query("select vacancy from Vacancy vacancy where vacancy.user= :currentUser and vacancy.statusVacancy=:statusVacancy")
    List<Vacancy> findAllByUserAndStatusVacancy(StatusVacancy statusVacancy, User currentUser, Pageable pageable);

    @Query("select vacancy from Vacancy vacancy where vacancy.user= :currentUser and vacancy.nameCompany=:nameCompany")
    List<Vacancy> findAllByUserAndNameCompany(String nameCompany, User currentUser, Pageable pageable);

    @Query("select vacancy from Vacancy vacancy where vacancy.user= :currentUser and vacancy.lastChange<:verificationDate and vacancy.statusVacancy='WAITING_FEEDBACK'")
    List<Vacancy> findAllForEmailSending(User currentUser, LocalDate verificationDate);
}
