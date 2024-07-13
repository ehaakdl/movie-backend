package com.movie.backend.model.dto;

import java.text.ParseException;
import java.util.Date;

import com.movie.backend.model.entity.eMovieApiProviderType;
import com.movie.backend.model.entity.movie.MovieEntity;
import com.movie.backend.utils.DateUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MovieSearchItemResponse {
    private final String movieName;
    private final Date createdAt;
    private final String director;
    private final Date openAt;

    public static MovieSearchItemResponse convertToMovieSearchItemResponseByApiProviderType(MovieEntity movieEntity,
            eMovieApiProviderType providerType) {
        if (providerType.equals(eMovieApiProviderType.kobis)) {
            try {
                return new MovieSearchItemResponse(
                        movieEntity.getKobisMovieName(),
                        movieEntity.getCreatedAt(),
                        movieEntity.getKobisDirectorName(),
                        DateUtils.parseDate(movieEntity.getKobisMovieOpenDate()));
            } catch (ParseException e) {
                throw new IllegalArgumentException("openDt 시간 변환에 실패하여 응답객체 생성 실패했습니다.", e);
            }
        } else {
            throw new IllegalArgumentException("지원하지 않는 타입입니다.");
        }
    }
}