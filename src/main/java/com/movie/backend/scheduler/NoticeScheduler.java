package com.movie.backend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.service.notice.EmailNoticeServiceImpl;

import lombok.RequiredArgsConstructor;

// 초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7) 
// 요일은 * 대신 ?가능
// https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
@Component
@RequiredArgsConstructor
public class NoticeScheduler {

        private final EmailNoticeServiceImpl emailNoticeService;

        @Scheduled(cron = "* * * * * *")
        @Transactional
        public void notice() {
                emailNoticeService.notice();
        }
}
