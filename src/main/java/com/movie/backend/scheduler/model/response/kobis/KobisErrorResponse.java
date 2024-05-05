package com.movie.backend.scheduler.model.response.kobis;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class KobisErrorResponse {
    @JsonProperty("faultInfo")
    private KobisErrorDetailResponse errorDetail;

    public static KobisErrorResponse readJson(String contents) {
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            return objectMapper.readValue(contents, KobisErrorResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

}
