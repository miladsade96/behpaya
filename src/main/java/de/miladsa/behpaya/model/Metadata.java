package de.miladsa.behpaya.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Metadata {
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private User createdBy;

    @LastModifiedBy
    private User updatedBy;

    @Column(nullable = false)
    private Boolean isHidden;
}
