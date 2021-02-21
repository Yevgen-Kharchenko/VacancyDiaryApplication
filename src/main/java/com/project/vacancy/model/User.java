package com.project.vacancy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "Email", name = "users_unique_email_idx"))
public class User extends BaseEntity{

    @Size(min=1, max = 30)
    private String name;
    @Email
    private String email;
    @Size(min=3,max = 60)
    private String password;
}
