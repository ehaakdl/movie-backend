package com.movie.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.backend.model.entity.notice.NoticeHistoryEntity;

public interface NoticeHistoryRepository extends JpaRepository<NoticeHistoryEntity, Integer> {
}
