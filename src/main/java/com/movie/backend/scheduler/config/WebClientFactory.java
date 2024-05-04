package com.movie.backend.scheduler.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientFactory {
    @Value("${scheduler.kobis.base-url}")
    private String kobisBaseUrl;
    @Value("${scheduler.kobis.api-key}")
    private String kobisApiKey;

    @Bean
    public WebClient kobisWebClient() {
        return WebClient.builder()
                .baseUrl(kobisBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("key", kobisApiKey))
                .build();
    }
}
