package com.movie.backend.service.notice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AlreadyRegisterUserException extends RuntimeException {
    private final String email;
}
