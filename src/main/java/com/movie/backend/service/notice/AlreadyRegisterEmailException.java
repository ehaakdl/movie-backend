package com.movie.backend.service.notice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AlreadyRegisterEmailException extends RuntimeException {
    private final String email;
}
