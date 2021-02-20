package com.project.vacancy.model;

import com.project.vacancy.model.enums.StatusVacancy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vacancy")
public class Vacancy extends BaseEntity{

    private String nameCompany;
    private String position;
    private Integer expectedSalary;
    private String linkToVacancy;
    private String recruitersContacts;
    private LocalDate lastChange;

    @Enumerated(value = EnumType.STRING)
    private StatusVacancy statusVacancy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private ApplicationUser user;


}
