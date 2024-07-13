package com.movie.backend.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MovieSearchResponse {
    private final long totalCount;
    private final long totalPage;
    private final List<MovieSearchItemResponse> items;
}
