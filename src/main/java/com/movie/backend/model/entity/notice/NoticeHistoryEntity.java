package com.movie.backend.model.entity.notice;

import com.movie.backend.model.entity.UserEntity;
import com.movie.backend.model.entity.audit.CreationUpdateAuditEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notice_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
public class NoticeHistoryEntity  extends CreationUpdateAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private eNoticeHistoryType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    public static NoticeHistoryEntity create(UserEntity userEntity) {
        return NoticeHistoryEntity.builder()
                .user(userEntity)
                .type(eNoticeHistoryType.email)
                .build();
    }

}
