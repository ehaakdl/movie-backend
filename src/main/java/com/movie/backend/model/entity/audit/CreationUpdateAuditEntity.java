package com.movie.backend.model.entity.audit;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class CreationUpdateAuditEntity {
    @CreatedDate
    @Column
    private Date createdAt;

    @LastModifiedDate
    @Column
    private Date updatedAt;
}
