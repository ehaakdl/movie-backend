package com.mose.movie.controller;

import com.mose.movie.etc.define.ErrorResponseException;
import com.mose.movie.etc.define.ResponseDTO;
import com.mose.movie.etc.define.eResponseErrorInfo;
import com.mose.movie.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(ErrorResponseException.class)
    private ResponseEntity<ResponseDTO> apiError(ErrorResponseException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ResponseDTO(
                        e.getMessage()
                        , e.isSuccess()));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ResponseDTO> ExceptionError(Exception e) {
        log.error(e.getMessage());
        log.error(CommonUtils.getStackTraceElements(e));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(
                        eResponseErrorInfo.DEFAULT.getMessage()
                        , false));
    }
}
