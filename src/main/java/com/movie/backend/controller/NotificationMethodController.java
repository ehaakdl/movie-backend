package com.movie.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        emailNoticeService.saveNotificationMethod(request.getEmail());
        return ResponseEntity.ok(CommonResponse.empty());
    }
}
