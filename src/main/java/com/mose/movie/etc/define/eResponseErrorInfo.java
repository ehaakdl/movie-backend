package com.mose.movie.etc.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum eResponseErrorInfo {
    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러 입니다."),
    BAD_PARAM(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "잘못된 파라미터가 넘어왔습니다."),
    SIGNUP_FAIL(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "회원가입 실패"),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "로그인 실패");
    private final int httpCode;
    private final HttpStatus httpStatus;
    private final String message;
}