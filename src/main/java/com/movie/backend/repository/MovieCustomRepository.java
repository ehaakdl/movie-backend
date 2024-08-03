package com.movie.backend.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.model.dto.MovieDTO;
import com.movie.backend.model.dto.MoviesDTO;
import com.movie.backend.model.entity.eMovieApiProviderType;
import com.movie.backend.model.entity.movie.MovieEntity;
import com.movie.backend.model.entity.movie.QMovieEntity;
import com.movie.backend.model.request.MovieSearchRequest;
import com.movie.backend.model.type.eSortOrderType;
import com.movie.backend.utils.PagingUtils;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MovieCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private ConstructorExpression<MovieDTO> createProjectionForProvider(
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

        return Projections.constructor(MovieDTO.class, expr.toArray(new SimpleExpression[0]));
    }

    private OrderSpecifier<?> createSortOrder(String filedName, eSortOrderType sortOrderType) {
        if(StringUtils.isBlank(filedName)){
            filedName = "createdAt";
        }
        if(sortOrderType == null){
            sortOrderType = eSortOrderType.desc;
        }

        QMovieEntity entity = QMovieEntity.movieEntity;
        switch (filedName) {
            case "createdAt":
                if (sortOrderType.equals(eSortOrderType.desc)) {
                    return entity.createdAt.desc();
                } else {
                    return entity.createdAt.asc();
                }

            default:
                throw new IllegalArgumentException(sortOrderType + "지원안하는 정렬입니다.");
        }

    }
    
    private BooleanExpression betweenCreatedAt(Date startAt, Date endAt) {
        if (startAt == null || endAt == null) {
            return null;
        }
        return QMovieEntity.movieEntity.createdAt.between(startAt, endAt);
    }

    private BooleanExpression eqApiProviderType(eMovieApiProviderType type) {
        if (type == null) {
            return null;
        }
        return QMovieEntity.movieEntity.apiProviderType.eq(type);
    }

    @Transactional
    public MoviesDTO search(MovieSearchRequest request) {
        eMovieApiProviderType providerType = request.getProvider();
        Date startAt = request.getStartAt();
        Date endAt = request.getEndAt();
        QMovieEntity movieEntity = QMovieEntity.movieEntity;

        long count = jpaQueryFactory.selectFrom(movieEntity).where(
                eqApiProviderType(providerType),
                betweenCreatedAt(startAt, endAt))
                .fetch().size();

        int pageSize = request.getPageSize();
        int page = request.getPage();
        int offset = PagingUtils.getOffset(count, page, pageSize);
        String sortBy = request.getSortBy();
        eSortOrderType sortOrder = request.getSortOrder();
        ConstructorExpression<MovieDTO> movieExpr = createProjectionForProvider(providerType);
        OrderSpecifier<?> orderSpecifier = createSortOrder(sortBy,sortOrder);
        List<MovieDTO> movies = jpaQueryFactory.select(movieExpr)
                .from(movieEntity)
                .where(
                        eqApiProviderType(providerType),
                        betweenCreatedAt(startAt, endAt))
                .orderBy(orderSpecifier)
                .offset(offset)
                .limit(pageSize)
                .fetch();

        return new MoviesDTO(movies, count);
    }
}
