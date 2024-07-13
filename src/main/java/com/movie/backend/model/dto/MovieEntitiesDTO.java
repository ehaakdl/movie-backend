package com.movie.backend.model.dto;

import java.util.List;

import com.movie.backend.model.entity.movie.MovieEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MovieEntitiesDTO {
    private final List<MovieEntity> movies;
    private final long totalCount;    
}
