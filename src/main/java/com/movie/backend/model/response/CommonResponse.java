package com.movie.backend.model.response;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access =AccessLevel.PROTECTED)
@AllArgsConstructor(access =AccessLevel.PROTECTED)
@Builder
@Getter
public class CommonResponse {
    private Object data;
    private Date issuedAt;

    public static CommonResponse create(Object data){
        return CommonResponse.builder().data(data).issuedAt(new Date()).build();
    }
}
