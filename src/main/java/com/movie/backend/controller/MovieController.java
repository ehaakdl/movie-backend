package com.movie.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.backend.model.dto.MovieSearchRequest;
import com.movie.backend.model.response.CommonResponse;
import com.movie.backend.service.movie.MovieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/search")
    public CommonResponse search(@Valid MovieSearchRequest request) {
        return CommonResponse.create(movieService.search(request));
    }
}
