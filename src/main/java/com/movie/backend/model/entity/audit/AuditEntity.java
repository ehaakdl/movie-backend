package com.movie.backend.model.entity.audit;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// TODO cratedBy, updatedBy,deletedBy 추가하기
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class AuditEntity {
    @CreatedDate
    @Column
    private Date createdAt;

    @LastModifiedDate
    @Column
    private Date updatedAt;

    @Column
    private Date deletedAt;

    public void delete(){
        this.deletedAt = new Date();
    }
}
