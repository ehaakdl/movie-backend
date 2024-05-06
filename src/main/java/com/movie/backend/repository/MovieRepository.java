package com.movie.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.backend.model.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
}
