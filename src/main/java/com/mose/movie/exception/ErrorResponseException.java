package com.mose.movie.exception;

import com.mose.movie.etc.define.eResponseErrorInfo;
import lombok.Getter;

@Getter
public class ErrorResponseException extends RuntimeException {
    private final eResponseErrorInfo errorResponseInfo;
    private final boolean success;

    public ErrorResponseException(final eResponseErrorInfo e, final boolean success) {
        this.errorResponseInfo = e;
        this.success = success;
    }

    public ErrorResponseException(Throwable cause, final eResponseErrorInfo e, boolean success) {
        super(cause);
        this.success = success;
        this.errorResponseInfo = e;
    }
}
