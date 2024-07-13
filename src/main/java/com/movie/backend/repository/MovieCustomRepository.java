package com.movie.backend.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.model.dto.MovieEntitiesDTO;
import com.movie.backend.model.dto.MovieSearchRequest;
import com.movie.backend.model.entity.eMovieApiProviderType;
import com.movie.backend.model.entity.movie.MovieEntity;
import com.movie.backend.model.entity.movie.QMovieEntity;
import com.movie.backend.utils.PagingUtils;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MovieCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private ConstructorExpression<MovieEntity> createProjectionForProvider(
            eMovieApiProviderType providerType) {
        QMovieEntity movieEntity = QMovieEntity.movieEntity;

        List<Expression<?>> expr = new ArrayList<>();
        if (providerType == eMovieApiProviderType.kobis) {
            expr.add(movieEntity.kobisDirectorName);
            expr.add(movieEntity.kobisMovieName);
            expr.add(movieEntity.kobisMovieOpenDate);
            expr.add(movieEntity.createdAt);
        } else {
            throw new IllegalArgumentException("kobis를 제외한 API는 아직 제공하지 않습니다.");
        }

        return Projections.constructor(MovieEntity.class, expr.toArray(new SimpleExpression[0]));
    }

    private OrderSpecifier<?> createOrderType(QMovieEntity entity, String order) {
        if (order.equals("desc")) {
            return entity.createdAt.desc();
        } else if (order.equals("asc")) {
            return entity.createdAt.asc();
        } else {
            throw new IllegalArgumentException(order + "지원안하는 정렬 유형입니다.");
        }

    }

    private OrderSpecifier<?> createOrderBySpec(String field, String order) {
        QMovieEntity movieEntity = QMovieEntity.movieEntity;
        if (field.equals(movieEntity.createdAt.toString())) {
            return createOrderType(movieEntity, order);
        } else {
            throw new IllegalArgumentException("지원 안하는 정렬 필드 입니다.");
        }
    }

    @Transactional
    public MovieEntitiesDTO search(MovieSearchRequest request) {
        eMovieApiProviderType providerType = request.getProvider();
        Date startAt = request.getStartAt();
        Date endAt = request.getEndAt();
        QMovieEntity movieEntity = QMovieEntity.movieEntity;

        // fetchCount 함수가 복잡한 쿼리에서는 잘 동작안함
        // fetch 로 가져와서 size 구해야함
        long count = jpaQueryFactory.selectFrom(movieEntity).where(
                movieEntity.apiProviderType.eq(providerType),
                movieEntity.createdAt.between(startAt, endAt))
                .fetch().size();

        int offset = PagingUtils.getOffset(count, request.getPage(), request.getPageSize());

        List<MovieEntity> movies = jpaQueryFactory.select(createProjectionForProvider(providerType))
                .from(movieEntity)
                .where(
                        movieEntity.apiProviderType.eq(providerType),
                        movieEntity.createdAt.between(startAt, endAt))
                .orderBy(createOrderBySpec(request.getSortBy(), request.getSortOrder()))
                .offset(offset)
                .limit(request.getPageSize())
                .fetch();
        
        return new MovieEntitiesDTO(movies, count);
    }
}
