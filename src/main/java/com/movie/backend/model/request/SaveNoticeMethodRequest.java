package com.movie.backend.model.request;

import com.movie.backend.model.entity.notice.eNoticeType;
import com.movie.backend.validation.EnumValue;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveNoticeMethodRequest {
    @Email
    @NotBlank
    private String email;

    @EnumValue(enumClass = eNoticeType.class, message = "잘못된 알림 유형입니다.", ignoreCase = true)
    private String method;
}
