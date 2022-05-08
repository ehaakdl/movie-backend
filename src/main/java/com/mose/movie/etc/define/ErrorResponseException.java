package com.mose.movie.etc.define;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponseException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
    private final boolean success;

    public ErrorResponseException(final eResponseErrorInfo e, final boolean success) {
        this.httpStatus = e.getHttpStatus();
        this.message = e.getMessage();
        this.success = success;
    }
}
