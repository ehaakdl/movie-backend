package com.movie.backend.scheduler.model.response.kobis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class KobisResponse {
    @JsonProperty("movieListResult")
    private KobisMoviesResponse data;

    public static KobisResponse readJson(String contents){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(contents, KobisResponse.class);
        } catch (Exception e) {
            return null;
        }
    }
}
