package com.movie.backend.repository.model;

import com.movie.backend.model.entity.notice.eNoticeHistoryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class NoticeHistoryVO {
    private Long userId;
    private eNoticeHistoryType type;
}
