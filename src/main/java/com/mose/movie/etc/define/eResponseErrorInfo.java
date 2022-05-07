package com.mose.movie.etc.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@ToString
@Getter
public enum eResponseErrorInfo {
    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessages.NOT_KNOW),
    BAD_PARAMETER(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ResponseMessages.BAD_PARAM),
    SIGNUP_FAIL(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ResponseMessages.SIGNUP_FAIL),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ResponseMessages.LOGIN_FAIL);
    private final int httpCode;
    private final HttpStatus httpStatus;
    private final String message;
}