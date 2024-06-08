package com.movie.backend.service.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.exception.EmailContentsGenerateFailException;
import com.movie.backend.model.entity.movie.MovieEntity;
import com.movie.backend.model.entity.notice.NoticeEntity;
import com.movie.backend.model.entity.notice.NoticeHistoryEntity;
import com.movie.backend.model.entity.notice.eNoticeType;
import com.movie.backend.model.vo.EmailMessageVO;
import com.movie.backend.repository.MovieRepository;
import com.movie.backend.repository.NoticeHistoryRepository;
import com.movie.backend.repository.NoticeRepository;
import com.movie.backend.service.EmailSendService;
import com.movie.backend.utils.ThymeleafUtils;
import com.movie.backend.utils.eThymeleafTemplateName;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeHistoryRepository noticeHistoryRepository;
    private final EmailSendService emailService;
    private final MovieRepository movieRepository;
    private final ThymeleafUtils thymeleafUtils;

    @Transactional
    public void saveAllHistory(List<String> emailList) {
        List<NoticeHistoryEntity> noticeHistoryEntities = emailList.stream().map(email -> {
            return NoticeHistoryEntity.create(email);
        }).collect(Collectors.toList());

        noticeHistoryRepository.saveAll(noticeHistoryEntities);
    }

    @Transactional
    public void notice() {
        List<NoticeEntity> noticeEntities = noticeRepository.findByType(eNoticeType.email);
        if (noticeEntities.isEmpty()) {
            return;
        }

        List<String> movies = movieRepository.findAll().stream()
                .map(MovieEntity::getKobisMovieName)
                .limit(20)
                .collect(Collectors.toList());

        // TODO 메일 알림 템플릿 작성
        noticeEntities.forEach(notice -> {
            Map<String, Object> model = new HashMap<>();
            model.put("movieNames", movies);

            String contents = thymeleafUtils.generate(eThymeleafTemplateName.email_notice, model);
            if (contents == null) {
                // TODO 스케줄러에서 예외 처리하는 곳에서 처리하기
                throw new EmailContentsGenerateFailException(eThymeleafTemplateName.email_notice, model);
            }

            EmailMessageVO message = EmailMessageVO.create(notice.getEmail(), "영화 정보", contents);
            emailService.send(message);
        });

        List<String> emailList = noticeEntities.stream().map(noticeEntity -> {
            return noticeEntity.getEmail();
        }).collect(Collectors.toList());

        saveAllHistory(emailList);
    }

    @Transactional
    public void saveNotificationMethod(String email) {
        if (noticeRepository.existsByEmail(email)) {
            throw new AlreadyRegisterEmailException(email);
        }

        noticeRepository.save(NoticeEntity.createEmailNotification(email));
    }

}
