package com.project.vacancy.dto;

import com.project.vacancy.model.enums.StatusVacancy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyResponse {

    private long id;
    private String nameCompany;
    private String position;
    private Integer expectedSalary;
    private String linkToVacancy;
    private String recruitersContacts;
    private LocalDate lastChange;
    private StatusVacancy statusVacancy;
}
