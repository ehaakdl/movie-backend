package com.mose.movie.controller;

import com.mose.movie.etc.define.ErrorResponseException;
import com.mose.movie.etc.define.ResponseDTO;
import com.mose.movie.etc.define.eResponseErrorInfo;
import com.mose.movie.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;


@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ErrorController {

    private final CommonUtils commonUtils;

    @ExceptionHandler(ErrorResponseException.class)
    private ResponseEntity<ResponseDTO> errorResponseException(ErrorResponseException e) {
        if(e.getCause() == null){
            log.error(commonUtils.getStackTraceElements(e));
        } else{
            log.error(commonUtils.getStackTraceElements(e.getCause()));
        }

        HttpStatus status = Optional
                .ofNullable(e.getErrorResponseInfo())
                .map(eResponseErrorInfo::getHttpStatus)
                .orElse(null);
        if(status == null){
            log.error("잘못된 HTTP status 코드입니다.");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        String message = Optional
                .ofNullable(e.getErrorResponseInfo())
                .map(eResponseErrorInfo::getMessage)
                .orElse(null);

        boolean success = e.isSuccess();

        return ResponseEntity
                .status(status)
                .body(new ResponseDTO(
                        message,
                        success));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ResponseDTO> ExceptionError(Exception e) {
        log.error(commonUtils.getStackTraceElements(e));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(
                        eResponseErrorInfo.DEFAULT.getMessage()
                        , false));
    }
}
