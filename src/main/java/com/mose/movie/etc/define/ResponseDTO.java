package com.mose.movie.etc.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDTO {
    protected final String message;
    protected final boolean success;
}
