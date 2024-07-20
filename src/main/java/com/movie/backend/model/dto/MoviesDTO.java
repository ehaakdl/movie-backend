package com.movie.backend.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MoviesDTO {
    private final List<MovieDTO> movies;
    private final long totalCount;    
}
