package com.movie.backend.model.entity.notice;

import com.movie.backend.model.entity.audit.CreationUpdateAuditEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "notice")
@Getter
public class NoticeEntity extends CreationUpdateAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private eNoticeType type;
}
