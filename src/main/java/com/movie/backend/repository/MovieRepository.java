package com.movie.backend.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.backend.model.entity.movie.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    boolean existsByKobisMovieCode(String code); 
    int countByCreatedAtAfter(Date time);

    Optional<MovieEntity> findFirstByOrderByIdDesc();
    Optional<MovieEntity> findFirstByOrderByIdAsc();
}
