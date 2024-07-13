package com.movie.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.movie.backend.model.dto.UserRegisterRequest;
import com.movie.backend.model.response.CommonResponse;
import com.movie.backend.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CommonResponse save(@Valid @RequestBody UserRegisterRequest request) {
        userService.register(request.getEmail());

        return CommonResponse.create("성공적으로 저장되었습니다.");
    }
}
