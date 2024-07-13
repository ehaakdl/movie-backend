package com.movie.backend.service.movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.exception.ThymeleafGenerateFailException;
import com.movie.backend.model.entity.notice.eNoticeHistoryType;
import com.movie.backend.model.vo.EmailMessageVO;
import com.movie.backend.repository.NoticeCustomRepository;
import com.movie.backend.repository.model.NoticeHistoryVO;
import com.movie.backend.repository.model.UserNoticeHistoryVO;
import com.movie.backend.service.EmailSendingService;
import com.movie.backend.utils.ThymeleafUtils;
import com.movie.backend.utils.eThymeleafTemplateName;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EmailNoticeService {
    private final ThymeleafUtils thymeleafUtils;
    private final NoticeCustomRepository noticeCustomRepository;
    private final EmailSendingService emailSendingService;

    @Transactional
    public void notice() {        
        // 유저별 최근 알림 이력을 조회한다.
        // 알림이력이 없어도 유저는 조회된다.
        List<UserNoticeHistoryVO> userNoticeHistories = noticeCustomRepository.getRecentNotificationsOrNone();
        if (userNoticeHistories.isEmpty()) {
            return;
        }

        // 알림 이력 저장
        // 메일 전송 코드에서 실패하더라도 이력은 남긴다.
        List<NoticeHistoryVO> noticeHistories = userNoticeHistories.stream()
        .map(item -> new NoticeHistoryVO(item.getUserId(), eNoticeHistoryType.email))
        .collect(Collectors.toList());
        // jpa가 아닌 jdbcTemplate 사용하여 조회했기 때문에 bulk insert도 jdbctTemplate로 구현됨
        noticeCustomRepository.bulkInsert(noticeHistories);

        // TODO 비동기로 작성
        userNoticeHistories.forEach(userNoticeHistory -> {
            Map<String, Object> model = new HashMap<>();
            // TODO 영화 조회 API 개발 필요
            model.put("query", "영화 조회 API 필요");

            // html 생성
            String contents = thymeleafUtils.generate(eThymeleafTemplateName.email_notice, model);
            if (contents == null) {
                throw new ThymeleafGenerateFailException(eThymeleafTemplateName.email_notice, model);
            }

            EmailMessageVO message = EmailMessageVO.create(userNoticeHistory.getEmail(), "영화 정보", contents);
            emailSendingService.send(message);
        });
        
        
    }
}
