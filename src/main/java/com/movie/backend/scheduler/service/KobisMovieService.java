package com.movie.backend.scheduler.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.model.entity.MovieEntity;
import com.movie.backend.model.entity.eMovieApiProviderType;
import com.movie.backend.repository.MovieRepository;
import com.movie.backend.scheduler.model.response.kobis.KobisMovieResponse;
import com.movie.backend.scheduler.utils.mapper.MovieMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KobisMovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    private List<KobisMovieResponse> findUniqueMovie(List<KobisMovieResponse> target) {
        return target.stream().filter(movie -> !movieRepository.existsByKobisMovieCode(movie.getMovieCd())).toList();
    }

    @Transactional
    public void saveMovies(List<KobisMovieResponse> movies) {
        List<KobisMovieResponse> uniqueMovies = findUniqueMovie(movies);

        List<MovieEntity> movieEntities = uniqueMovies.stream()
                .map(movie -> movieMapper.toMovieEntity(movie, eMovieApiProviderType.kobis))
                .toList();

        movieRepository.saveAll(movieEntities);
    }
}
