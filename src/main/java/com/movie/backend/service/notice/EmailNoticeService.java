package com.movie.backend.service.notice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.model.entity.notice.NoticeEntity;
import com.movie.backend.model.entity.notice.eNoticeType;
import com.movie.backend.model.vo.EmailMessageVO;
import com.movie.backend.repository.NoticeRepository;
import com.movie.backend.service.EmailSendService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailNoticeService {
    private final NoticeRepository noticeRepository;
    private final EmailSendService emailService;

    @Transactional
    public void notice() {
        List<NoticeEntity> noticEntities = noticeRepository.findByType(eNoticeType.email);

        // TODO 메일 알림 템플릿 작성
        noticEntities.forEach(notice -> {
            EmailMessageVO message = EmailMessageVO.create(notice.getEmail(), "영화 정보", "body");
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
