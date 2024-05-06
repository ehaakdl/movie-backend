package com.movie.backend.repository;

import org.springframework.stereotype.Repository;

import com.movie.backend.model.entity.QUserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public boolean test(String email) {

        return jpaQueryFactory.select(QUserEntity.userEntity.email)
                .from(QUserEntity.userEntity)
                .where(
                    QUserEntity.userEntity.email.eq(email))
                .limit(1)
                .fetchOne() != null;
    }
}
