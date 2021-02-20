package com.project.vacancy.controller;

import com.project.vacancy.dto.VacancyRequest;
import com.project.vacancy.dto.VacancyResponse;
import com.project.vacancy.exeption.UserNotFoundException;
import com.project.vacancy.model.enums.StatusVacancy;
import com.project.vacancy.service.VacancyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/vacancy")
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping()
    public List<VacancyResponse> findUsersVacancies(@RequestParam("page") int currentPage) throws UserNotFoundException {
        log.info("Find current user`s vacancies");
        return vacancyService.findUsersVacancies(currentPage);
    }

    @PostMapping()
    public ResponseEntity<String> createVacancy(@RequestBody VacancyRequest vacancyRequest) throws UserNotFoundException {
        log.info("create new vacancy");
        vacancyService.createNewVacancy(vacancyRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public  ResponseEntity<String> updateVacancy(@RequestBody VacancyRequest vacancyRequest){
        log.info("Update vacancy");
        vacancyService.updateVacancy(vacancyRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws UserNotFoundException {
        log.info("delete vacancy");
        vacancyService.deleteVacancy(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/{statusVacancy}")
    public List<VacancyResponse> findUsersVacanciesByStatus(@PathVariable StatusVacancy statusVacancy,@RequestParam("page") int currentPage) throws UserNotFoundException {
        log.info("Find vacancies by status");
        return vacancyService.findUsersVacanciesByStatus(statusVacancy,currentPage);
    }

    @GetMapping("/company/{nameCompany}")
    public List<VacancyResponse> findUsersVacanciesByNameCompany(@PathVariable String nameCompany,@RequestParam("page") int currentPage) throws UserNotFoundException {
        log.info("Find vacancies by nameCompany");
        return vacancyService.findUsersVacanciesByNameCompany(nameCompany,currentPage);
    }
}
