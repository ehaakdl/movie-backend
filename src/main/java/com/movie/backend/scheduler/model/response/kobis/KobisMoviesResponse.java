package com.movie.backend.scheduler.model.response.kobis;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class KobisMoviesResponse {
    @JsonProperty("totCnt")
    private int totalCount;
    private String source;
    @JsonProperty("movieList")
    private List<KobisMovieResponse> movies;
}
