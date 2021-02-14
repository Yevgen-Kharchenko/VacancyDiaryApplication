package com.project.vacancy.model;

public interface EntityHasId {
    Long getId();

    void setId(Long id);

    boolean isNew();
}
