package com.movie.backend.scheduler;

import org.springframework.util.ErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchedulerErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable throwable) {
        log.error("에러발생 {}", throwable);
    }
}
