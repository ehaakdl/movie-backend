package com.movie.backend.service.movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.exception.EmailContentsGenerateFailException;
import com.movie.backend.model.entity.UserEntity;
import com.movie.backend.model.entity.movie.MovieEntity;
import com.movie.backend.model.entity.notice.NoticeHistoryEntity;
import com.movie.backend.model.vo.EmailMessageVO;
import com.movie.backend.repository.MovieRepository;
import com.movie.backend.repository.NoticeHistoryRepository;
import com.movie.backend.repository.UserRepository;
import com.movie.backend.service.EmailSendingService;
import com.movie.backend.utils.ThymeleafUtils;
import com.movie.backend.utils.eThymeleafTemplateName;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationService {
    private final UserRepository userRepository;
    private final NoticeHistoryRepository noticeHistoryRepository;
    private final EmailSendingService emailService;
    private final MovieRepository movieRepository;
    private final ThymeleafUtils thymeleafUtils;

    @Transactional
    public void saveAllHistory(List<UserEntity> userEntities) {
        List<NoticeHistoryEntity> noticeHistoryEntities = userEntities.stream().map(userEntity -> {
            return NoticeHistoryEntity.create(userEntity);
        }).collect(Collectors.toList());

        noticeHistoryRepository.saveAll(noticeHistoryEntities);
    }

    @Transactional
    public void notice() {
        List<UserEntity> userEntities = userRepository.findAllByDeletedAtIsNull();
        if (userEntities.isEmpty()) {
            return;
        }

        List<String> movies = movieRepository.findAll().stream()
                .map(MovieEntity::getKobisMovieName)
                .limit(20)
                .collect(Collectors.toList());

        // TODO 메일 알림 템플릿 작성
        userEntities.forEach(notice -> {
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

        saveAllHistory(userEntities);
    }
}
