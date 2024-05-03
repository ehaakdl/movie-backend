package com.movie.backend.repository;

import org.springframework.stereotype.Repository;

import com.movie.backend.model.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public boolean test(String email) {

        return jpaQueryFactory.select(QUser.user.email)
                .from(QUser.user)
                .where(
                        QUser.user.email.eq(email))
                .limit(1)
                .fetchOne() != null;
    }
}
