package com.project.vacancy.dto;

import com.project.vacancy.model.enums.StatusVacancy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyRequest {

    private long id;
    private String nameCompany;
    private String position;
    private Integer expectedSalary;
    private String linkToVacancy;
    private String recruitersContacts;
    private StatusVacancy statusVacancy;
}
