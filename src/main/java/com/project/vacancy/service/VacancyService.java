package com.project.vacancy.service;

import com.project.vacancy.dto.VacancyRequest;
import com.project.vacancy.dto.VacancyResponse;
import com.project.vacancy.exeption.EntityNotExistRuntimeException;
import com.project.vacancy.exeption.UserNotFoundException;
import com.project.vacancy.model.ApplicationUser;
import com.project.vacancy.model.Vacancy;
import com.project.vacancy.model.enums.StatusVacancy;
import com.project.vacancy.repositiry.VacancyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyService {
    private final int PAGE_SIZE = 5;
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public List<VacancyResponse> findUsersVacancies(int currentPage) throws UserNotFoundException {
        ApplicationUser currentUser = userService.findCurrentUser();
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        List<Vacancy> vacancyList = vacancyRepository.findAllByUser(currentUser, pageable);

        return vacancyList.stream()
                .map(vacancy -> modelMapper.map(vacancy, VacancyResponse.class))
                .collect(Collectors.toList());
    }

    public void createNewVacancy(VacancyRequest vacancyRequest) throws UserNotFoundException {
        Vacancy newVacancy = modelMapper.map(vacancyRequest, Vacancy.class);
        newVacancy.setLastChange(LocalDate.now());
        newVacancy.setUser(userService.findCurrentUser());
        vacancyRepository.save(newVacancy);
    }

    public void updateVacancy(VacancyRequest vacancyRequest) {
        Vacancy oldVacancy = vacancyRepository.findById(vacancyRequest.getId())
                .orElseThrow(() -> new EntityNotExistRuntimeException("Vacancy not found"));
        Vacancy updatedVacancy = modelMapper.map(vacancyRequest, Vacancy.class);
        updatedVacancy.setUser(oldVacancy.getUser());
        if (!oldVacancy.getStatusVacancy().equals(updatedVacancy.getStatusVacancy())) {
            updatedVacancy.setLastChange(LocalDate.now());
        }else{
            updatedVacancy.setLastChange(oldVacancy.getLastChange());
        }
        vacancyRepository.save(updatedVacancy);
    }

    public void deleteVacancy(long id) throws UserNotFoundException {
        ApplicationUser currentUser=userService.findCurrentUser();
        Vacancy vacancy=vacancyRepository.findById(id)
                .orElseThrow(()->new EntityNotExistRuntimeException("Vacancy not found"));
        if(vacancy.getUser().equals(currentUser))
        vacancyRepository.deleteById(id);
    }

    public List<VacancyResponse> findUsersVacanciesByStatus(StatusVacancy statusVacancy, int currentPage) throws UserNotFoundException {
        ApplicationUser currentUser = userService.findCurrentUser();
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        List<Vacancy> vacancyListByStatus = vacancyRepository.findAllByUserAndStatusVacancy(statusVacancy,currentUser, pageable);

        return vacancyListByStatus.stream()
                .map(vacancy -> modelMapper.map(vacancy, VacancyResponse.class))
                .collect(Collectors.toList());
    }

    public List<VacancyResponse> findUsersVacanciesByNameCompany(String nameCompany, int currentPage) throws UserNotFoundException {
        ApplicationUser currentUser = userService.findCurrentUser();
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        List<Vacancy> vacancyListByNameCompany = vacancyRepository.findAllByUserAndNameCompany(nameCompany,currentUser, pageable);

        return vacancyListByNameCompany.stream()
                .map(vacancy -> modelMapper.map(vacancy, VacancyResponse.class))
                .collect(Collectors.toList());
    }

}
