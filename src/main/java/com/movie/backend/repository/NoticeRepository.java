package com.movie.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.backend.model.entity.notice.NoticeEntity;
import com.movie.backend.model.entity.notice.eNoticeType;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {
    List<NoticeEntity> findByType(eNoticeType type);

    boolean existsByEmail(String email);
}
