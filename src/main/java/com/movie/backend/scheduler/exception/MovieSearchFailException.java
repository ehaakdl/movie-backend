package com.movie.backend.scheduler.exception;

import com.movie.backend.scheduler.model.response.kobis.KobisErrorResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MovieSearchFailException extends RuntimeException{
    private final KobisErrorResponse response;
}
