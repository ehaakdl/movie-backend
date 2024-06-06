package com.movie.backend.service.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.exception.EmailContentsGenerateFailException;
import com.movie.backend.model.entity.notice.NoticeEntity;
import com.movie.backend.model.entity.notice.eNoticeType;
import com.movie.backend.model.vo.EmailMessageVO;
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
    private final EmailSendService emailService;
    private final ThymeleafUtils thymeleafUtils;
    @Transactional
    public void notice() {
        List<NoticeEntity> noticEntities = noticeRepository.findByType(eNoticeType.email);

        // TODO 메일 알림 템플릿 작성
        noticEntities.forEach(notice -> {
            Map<String, String> model = new HashMap<>();
            model.put("key", "key입니다.");
            model.put("value", "값입니다.");

            String contents = thymeleafUtils.generate(eThymeleafTemplateName.email_notice, model);
            if(contents == null){
                // TODO 스케줄러에서 예외 처리하는 곳에서 처리하기
                throw new EmailContentsGenerateFailException(eThymeleafTemplateName.email_notice, model);
            }

            EmailMessageVO message = EmailMessageVO.create(notice.getEmail(), "영화 정보", contents);
            emailService.send(message);
        });
    }

    @Transactional
    public void saveNotificationMethod(String email) {
        if(noticeRepository.existsByEmail(email)){
            throw new AlreadyRegisterEmailException(email);
        }
        
        noticeRepository.save(NoticeEntity.createEmailNotification(email));
    }

}
