package com.movie.backend.scheduler.model.response.kobis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class KobisErrorDetailResponse {
    private String message;
    private String errorCode;

}
