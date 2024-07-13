package com.movie.backend.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.movie.backend.model.entity.eMovieApiProviderType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchRequest {
    @NotNull
    private eMovieApiProviderType provider;
    // desc/asc
    private String sortOrder;
    // 필드명
    private String sortBy;
    private String movieName;

    @Min(1)
    private int page = 1;
    @Max(100)
    private int pageSize = 100;
    
    // created_at 필드를 바라본다.
    @DateTimeFormat(pattern = "yyyy-mm-dd'T'HH:mm:ss")
    private Date startAt;
    @DateTimeFormat(pattern = "yyyy-mm-dd'T'HH:mm:ss")
    private Date endAt;

}