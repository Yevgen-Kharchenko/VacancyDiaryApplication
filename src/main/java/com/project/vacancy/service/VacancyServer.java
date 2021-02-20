package com.project.vacancy.service;

import com.project.vacancy.dto.VacancyRequest;
import com.project.vacancy.dto.VacancyResponse;
import com.project.vacancy.exeption.UserNotFoundException;
import com.project.vacancy.model.enums.StatusVacancy;

import java.util.List;

public interface VacancyServer {
    List<VacancyResponse> findUsersVacancies(int currentPage) throws UserNotFoundException;

    void createNewVacancy(VacancyRequest vacancyRequest) throws UserNotFoundException;

    void updateVacancy(VacancyRequest vacancyRequest);

    void deleteVacancy(long id) throws UserNotFoundException;

    List<VacancyResponse> findUsersVacanciesByStatus(StatusVacancy statusVacancy, int currentPage) throws UserNotFoundException;

    List<VacancyResponse> findUsersVacanciesByNameCompany(String nameCompany, int currentPage) throws UserNotFoundException;

    void sendEmail() throws UserNotFoundException;
}
