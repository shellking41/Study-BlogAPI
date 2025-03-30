package org.study.studyblogapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract  class BaseEntity {

    @CreatedBy
    @Column(updatable = false,nullable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;



    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
