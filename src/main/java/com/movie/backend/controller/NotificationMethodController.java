package com.movie.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.backend.model.entity.notice.eNoticeType;
import com.movie.backend.model.request.SaveNoticeMethodRequest;
import com.movie.backend.model.response.CommonResponse;
import com.movie.backend.service.notice.EmailNoticeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notification/method")
@RequiredArgsConstructor
public class NotificationMethodController {
    private final EmailNoticeService emailNoticeService;

    @PostMapping
    public ResponseEntity<CommonResponse> save(@Valid @RequestBody SaveNoticeMethodRequest request) {
        if(eNoticeType.email.name().equals(request.getMethod())){
            emailNoticeService.saveNotificationMethod(request.getEmail());
        }else{
            // 유효성 검사가 전처리되기 때문에 해당 코드가 실행될수 없음.
            // 만약된 실행된다면 로직상 에러다.
            throw new RuntimeException();
        }
        
        return ResponseEntity.ok(CommonResponse.empty());
    }
}
