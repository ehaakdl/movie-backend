package com.movie.backend.model.response;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.movie.backend.model.response.type.eResponseStatusCodeType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Getter
public class CommonResponse {
    private Object contents;
    private final Date issuedAt;
    private final eResponseStatusCodeType status;
    private Object error;
    private String message;

    public static CommonResponse create(String message) {
        return CommonResponse.builder().message(message).status(eResponseStatusCodeType.success).issuedAt(new Date())
                .build();
    }

    public static CommonResponse create(Object data) {
        return CommonResponse.builder().contents(data).status(eResponseStatusCodeType.success).issuedAt(new Date()).build();
    }

    public static CommonResponse empty() {
        return CommonResponse.builder().status(eResponseStatusCodeType.success).issuedAt(new Date()).build();
    }

    public static CommonResponse emptyError() {
        return CommonResponse.builder().status(eResponseStatusCodeType.fail).issuedAt(new Date()).build();
    }

    public static CommonResponse createError(Map<String, String> error, String message) {
        return CommonResponse.builder().error(error).message(message).status(eResponseStatusCodeType.fail)
                .issuedAt(new Date()).build();
    }

    public static CommonResponse createError(String message) {
        return CommonResponse.builder().message(message).status(eResponseStatusCodeType.fail).issuedAt(new Date())
                .build();
    }
}
