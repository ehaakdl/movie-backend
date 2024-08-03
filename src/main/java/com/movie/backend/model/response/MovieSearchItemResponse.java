package com.movie.backend.model.response;

import java.text.ParseException;
import java.util.Date;

import com.movie.backend.model.dto.MovieDTO;
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
    private final String genre;

    public static MovieSearchItemResponse convertToMovieSearchItemResponseByApiProviderType(MovieDTO movie,
            eMovieApiProviderType providerType) {
        if (providerType.equals(eMovieApiProviderType.kobis)) {
            try {
                Date openAt = DateUtils.parseDate(movie.getKobisMovieOpenDate(), DateUtils.FORMAT_2);
                return new MovieSearchItemResponse(
                        movie.getKobisMovieName(),
                        movie.getCreatedAt(),
                        movie.getKobisDirectorName(),
                        openAt,
                        movie.getKobisRepGenreName());
            } catch (ParseException e) {
                throw new IllegalArgumentException("openDt 시간 변환에 실패하여 응답객체 생성 실패했습니다.", e);
            }
        } else {
            throw new IllegalArgumentException("지원하지 않는 타입입니다.");
        }
    }
}