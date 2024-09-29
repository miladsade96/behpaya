package de.miladsa.behpaya.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AvailableTranslations {
    @Column(columnDefinition = "varchar(1000)", nullable = false)
    private String fa;

    @Column(columnDefinition = "varchar(1000)", nullable = false)
    private String en;
}
