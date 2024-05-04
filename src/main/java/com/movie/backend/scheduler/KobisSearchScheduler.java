package com.movie.backend.scheduler;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7) 
// 요일은 * 대신 ?가능
// https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
@Component
@Slf4j
@RequiredArgsConstructor
public class KobisSearchScheduler {
    @Value("${scheduler.kobis.movie-weekly-info-url}")
    private String movieWeeklySearchUrl;
    @Value("${scheduler.kobis.movie-info-url}")
    private String movieInfoSearchUrl;
    
    private final WebClient kobisWebClient;

    @Scheduled(cron = "59 * * * * *")
    public void cronJobSch() throws InterruptedException {
        
        kobisWebClient.get(movieInfoSearchUrl).get();
    }
}
