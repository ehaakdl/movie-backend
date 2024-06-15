package com.movie.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.model.entity.QUserEntity;
import com.movie.backend.model.entity.UserEntity;
import com.movie.backend.model.entity.notice.QNoticeHistoryEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 알림 이력이 없는 유저들 찾기
     */
    @Transactional
    public List<UserEntity> getUsersByEmptyNoticeHistory() {
        return jpaQueryFactory.select(QUserEntity.userEntity)
                .from(QUserEntity.userEntity)
                .innerJoin(QNoticeHistoryEntity.noticeHistoryEntity)
                .on(QUserEntity.userEntity.ne((QNoticeHistoryEntity.noticeHistoryEntity.user)))
                .where(QUserEntity.userEntity.deletedAt.isNull())
                .fetch();
    }
}
