package com.movie.backend.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MovieDTO {
    private final String kobisDirectorName;
    private final String kobisMovieName;
    private final String kobisMovieOpenDate;
    private final Date createdAt;    
}
