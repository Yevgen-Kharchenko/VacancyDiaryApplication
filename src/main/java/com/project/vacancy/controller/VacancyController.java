package com.project.vacancy.controller;

import com.project.vacancy.dto.VacancyRequest;
import com.project.vacancy.dto.VacancyResponse;
import com.project.vacancy.model.enums.StatusVacancy;
import com.project.vacancy.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/vacancy")
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping()
    public ResponseEntity<List<VacancyResponse>> findUsersVacancies(@RequestParam("page") int currentPage) {
        log.info("Find current user`s vacancies");
        return ResponseEntity.ok().body(vacancyService.findUsersVacancies(currentPage));
    }

    @PostMapping
    public ResponseEntity createVacancy(@RequestBody VacancyRequest vacancyRequest) {
        log.info("create new vacancy");
        vacancyService.createNewVacancy(vacancyRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateVacancy(@RequestBody VacancyRequest vacancyRequest) {
        log.info("Update vacancy");
        vacancyService.updateVacancy(vacancyRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        log.info("delete vacancy");
        vacancyService.deleteVacancy(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/{statusVacancy}")
    public ResponseEntity<List<VacancyResponse>> findUsersVacanciesByStatus(
            @PathVariable StatusVacancy statusVacancy, @RequestParam("page") int currentPage) {
        log.info("Find vacancies by status");
        return ResponseEntity.ok().body(vacancyService.findUsersVacanciesByStatus(statusVacancy, currentPage));
    }

    @GetMapping("/company/{nameCompany}")
    public ResponseEntity<List<VacancyResponse>> findUsersVacanciesByNameCompany(
            @PathVariable String nameCompany, @RequestParam("page") int currentPage) {
        log.info("Find vacancies by nameCompany");
        return ResponseEntity.ok().body(vacancyService.findUsersVacanciesByNameCompany(nameCompany, currentPage));
    }

    @PostMapping("/send_email")
    public ResponseEntity sendMails() {
        log.info("Sending email");
        vacancyService.sendEmail();
        return ResponseEntity.ok().build();

    }
}
