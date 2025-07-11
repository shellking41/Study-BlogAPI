package org.study.studyblogapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class MediaFileBaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;



    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
