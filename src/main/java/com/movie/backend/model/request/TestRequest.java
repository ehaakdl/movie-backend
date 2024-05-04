package com.movie.backend.model.request;

import com.movie.backend.validation.Password;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TestRequest {
    @Password
    private String password;

    @NotBlank
    private String name;
}
