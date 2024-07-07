package com.movie.backend.repository.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserNoticeHistoryVO {
    private Date notifiedAt;
    private Long userId;
    private String email;
}
