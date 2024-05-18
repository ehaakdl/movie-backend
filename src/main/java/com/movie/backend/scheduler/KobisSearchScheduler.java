package com.movie.backend.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.movie.backend.scheduler.exception.MovieSearchFailException;
import com.movie.backend.scheduler.model.response.kobis.KobisErrorResponse;
import com.movie.backend.scheduler.model.response.kobis.KobisMoviesResponse;
import com.movie.backend.scheduler.model.response.kobis.KobisResponse;
import com.movie.backend.scheduler.service.KobisMovieService;

import lombok.RequiredArgsConstructor;

// 초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7) 
// 요일은 * 대신 ?가능
// https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
@Component
@RequiredArgsConstructor
public class KobisSearchScheduler {
        @Value("${kobis.base-url}")
        private String kobisBaseUrl;
        @Value("${kobis.api-key}")
        private String kobisApiKey;
        @Value("${kobis.movie-weekly-info-url}")
        private String kobisMovieWeeklySearchUrl;
        @Value("${kobis.movie-info-url}")
        private String kobisMovieInfoSearchUrl;

        private static final String KOBIS_QUERY_PARAM_KEY = "key";

        private final KobisMovieService kobisMovieService;

        @Scheduled(cron = "${scheduler.task.kobis-movie-search.cron}")
        @Transactional
        public void movieInfoCollectionJob() {
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

                KobisResponse kobisResponse = KobisResponse.readJson(body);
                if (kobisResponse == null) {
                        // TODO 에러 케이스 처리
                        // api 호출 횟수 제한
                        KobisErrorResponse errorResponse = KobisErrorResponse.readJson(body);
                        assert errorResponse != null;
                        throw new MovieSearchFailException(errorResponse);
                }

                KobisMoviesResponse moviesResponse = kobisResponse.getData();
                if (moviesResponse == null) {
                        throw new RuntimeException();
                }

                kobisMovieService.saveMovies(moviesResponse.getMovies());
        }
}
