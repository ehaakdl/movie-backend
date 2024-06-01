package com.movie.backend.model.response.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum eResponseStatusCodeType {
    success(1), fail(0);

    private final int code;
}
