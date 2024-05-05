package com.movie.backend.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.movie.backend.scheduler.exception.MovieSearchFailException;
import com.movie.backend.scheduler.model.response.kobis.KobisErrorResponse;
import com.movie.backend.scheduler.model.response.kobis.KobisResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7) 
// 요일은 * 대신 ?가능
// https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
@Component
@Slf4j
@RequiredArgsConstructor
public class KobisSearchScheduler {
        @Value("${scheduler.kobis.base-url}")
        private String kobisBaseUrl;
        @Value("${scheduler.kobis.api-key}")
        private String kobisApiKey;
        @Value("${scheduler.kobis.movie-weekly-info-url}")
        private String kobisMovieWeeklySearchUrl;
        @Value("${scheduler.kobis.movie-info-url}")
        private String kobisMovieInfoSearchUrl;

        private static final String KOBIS_QUERY_PARAM_KEY = "key";

        @Scheduled(cron = "* * * * * *")
        public void movieInfoSearchJob() throws InterruptedException {
                String url = UriComponentsBuilder.fromHttpUrl(kobisBaseUrl)
                                .path(kobisMovieInfoSearchUrl)
                                .queryParam(KOBIS_QUERY_PARAM_KEY, kobisApiKey)
                                .build()
                                .toUriString();

                WebClient kobisWebClient = WebClient.create(url);

                String body = kobisWebClient.get()
                                .retrieve()
                                .bodyToMono(String.class)
                                .block();

                KobisResponse movies = KobisResponse.readJson(body);
                if (movies == null) {
                        KobisErrorResponse errorResponse = KobisErrorResponse.readJson(body);
                        assert errorResponse != null;
                        throw new MovieSearchFailException(errorResponse);
                }

                log.info(movies.toString());
                // TODO 디비에 데이터 추가하고 async 함수에서 추가된 데이터 프로세싱하기
        }
}
