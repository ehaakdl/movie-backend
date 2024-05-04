package com.movie.backend.model.request;

import com.movie.backend.validation.Password;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TestRequest {
    // @NotNull
    @Password
    @Valid
    private String password;

    // @Max(30)
    // private Long number;
    @NotBlank
    private String name;
}
