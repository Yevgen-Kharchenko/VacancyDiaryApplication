package com.project.vacancy.controller;

import com.project.vacancy.dto.VacancyRequest;
import com.project.vacancy.dto.VacancyResponse;
import com.project.vacancy.exeption.UserNotFoundException;
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
}
