package com.movie.backend.service;

import java.nio.charset.StandardCharsets;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.movie.backend.exception.EmailSendFailException;
import com.movie.backend.model.vo.EmailMessageVO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void send(EmailMessageVO emailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
            String to = emailMessage.getTo(); // 메일 수신자
            String subject = emailMessage.getSubject(); // 메일 제목
            String body = emailMessage.getBody(); //메일 본문
            mimeMessageHelper.setTo(to); 
            mimeMessageHelper.setSubject(subject); 
            mimeMessageHelper.setText(body);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendFailException(e);
        } catch(MailSendException e){
            throw new EmailSendFailException(e);
        }
    }
}
