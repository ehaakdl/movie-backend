package com.movie.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.backend.model.entity.movie.MovieEntity;
import com.movie.backend.model.entity.movie.eKobisMovieProductStatus;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    boolean existsByKobisMovieCode(String code); 
    List<MovieEntity> findByKobisMovieProductStatus(eKobisMovieProductStatus status);
}
