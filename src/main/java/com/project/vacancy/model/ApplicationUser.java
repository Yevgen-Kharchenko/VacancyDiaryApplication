package com.project.vacancy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "Email", name = "users_unique_email_idx"))
public class ApplicationUser extends BaseEntity{

    @Size(min=1, max = 30)
    private String name;
    @Email
    private String email;

    private String password;
}
