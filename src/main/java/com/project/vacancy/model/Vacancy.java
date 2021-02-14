package com.project.vacancy.model;

import com.project.vacancy.model.enums.StatusVacancy;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vacancy")
public class Vacancy extends BaseEntity{

    @Column(name = "name_company")
    private String nameCompany;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private Integer expectedSalary;

    @Column(name = "link_to_vacancy")
    private String linkToVacancy;

    @Column(name = "recruiters_contacts")
    private String recruitersContacts;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_vacancy")
    private StatusVacancy statusVacancy;

    @Column(name = "last_change")
    private LocalDate lastChange;

    @Column(name = "history")
    private String history;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
