package com.movie.backend.model.entity.movie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum eKobisMovieProductStatus {
    released("개봉"),
    upcoming("개봉예정"),
    read_for_release("개봉준비"),
    other("기타");

    private final String displayCode;

    public static eKobisMovieProductStatus ofResponseProductStatus(String productStatus) {
        for (eKobisMovieProductStatus value : eKobisMovieProductStatus.values()) {
            if (value.getDisplayCode().equals(productStatus)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid product status: " + productStatus);

    }
}
