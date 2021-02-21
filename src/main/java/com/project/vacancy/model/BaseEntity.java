package com.project.vacancy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Access(value = AccessType.FIELD)
public class BaseEntity implements EntityHasId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Access(value = AccessType.PROPERTY)
    private Long id;

    @Override
    public boolean isNew() {
        return getId() == null || getId() == 0;
    }
}
