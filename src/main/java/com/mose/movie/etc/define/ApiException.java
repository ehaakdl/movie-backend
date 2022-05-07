package com.mose.movie.etc.define;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApiException extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;
    private final boolean success;
    private final JsonObject result;
    private Exception exception;

    public ApiException(final eResponseErrorInfo e, final boolean success) {
        this.httpStatus = e.getHttpStatus();
        this.message = e.getMessage();
        this.result = null;
        this.success = success;
    }

    public ApiException(final Exception exception, final eResponseErrorInfo e, final boolean success) {
        this.exception = exception;
        this.httpStatus = e.getHttpStatus();
        this.message = e.getMessage();
        this.result = null;
        this.success = success;
    }
}
