package com.movie.backend.service.filter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movie.backend.model.entity.movie.MovieEntity;
import com.movie.backend.model.entity.movie.eKobisMovieProductStatus;
import com.movie.backend.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieFilterService {
    private final MovieRepository movieRepository;
    public List<MovieEntity> filterUpcomingMovie(){
        return movieRepository.findByKobisMovieProductStatus(eKobisMovieProductStatus.other);
    }
}
