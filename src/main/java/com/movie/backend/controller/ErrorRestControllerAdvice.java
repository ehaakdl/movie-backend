package com.movie.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.movie.backend.exception.AlreadyRegisterUserException;
import com.movie.backend.model.response.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorRestControllerAdvice {
    private static String BAD_REQUEST_MESSAGE = "잘못된 요청입니다.";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResponse> unkownError(Exception exception) {
        log.error("500에러 발생", exception.getCause());
        return new ResponseEntity<>(
                CommonResponse.emptyError(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse> badRequestNotReadableError(Exception exception) {
        return new ResponseEntity<>(
                CommonResponse.createError(BAD_REQUEST_MESSAGE),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> badRequestNotValidError(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(
                CommonResponse.createError(errors, BAD_REQUEST_MESSAGE),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AlreadyRegisterUserException.class)
    public ResponseEntity<CommonResponse> badRequestAlereadyRegisterEmail(AlreadyRegisterUserException exception) {

        return new ResponseEntity<>(
                CommonResponse.createError("이미 등록된 이메일입니다."),
                HttpStatus.BAD_REQUEST);
    }

}
