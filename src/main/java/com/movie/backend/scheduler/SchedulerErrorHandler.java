package com.movie.backend.scheduler;

import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import com.movie.backend.scheduler.exception.MovieSearchFailException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SchedulerErrorHandler implements ErrorHandler {
    
    @Override
    public void handleError(Throwable throwable) {
        if (throwable instanceof MovieSearchFailException) {

            MovieSearchFailException exception = (MovieSearchFailException) throwable;
            String message = exception.getResponse().getErrorDetail().getMessage();
            String errorCode = exception.getResponse().getErrorDetail().getErrorCode();
            log.error("kobis 영화 정보 조회 실패 메시지={}, 에러코드={}", message, errorCode);
        }
        log.error("에러 스택", throwable);
    }
}
