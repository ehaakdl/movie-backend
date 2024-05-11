package com.movie.backend.model.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailMessageVO {
    private String to;
    private String subject;
    private String body;

    public static EmailMessageVO create(String to, String subject, String body){
        return EmailMessageVO.builder().body(body).to(to).subject(subject).build();
    }
}
