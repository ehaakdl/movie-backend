package com.movie.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.backend.model.entity.User;
import com.movie.backend.model.request.TestRequest;
import com.movie.backend.model.response.CommonResponse;
import com.movie.backend.repository.CustomRepository;
import com.movie.backend.repository.UserRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Transactional
@RequiredArgsConstructor
public class TestController {
    private final UserRepository userRepository;
    private final CustomRepository customRepository;

    @GetMapping("/api/v1/test")
    public String test() {
        User user = userRepository.findByEmail("test").orElseThrow(() -> new RuntimeException());
        return user.getEmail();
    }

    @PostMapping("/api/v1/post")
    public ResponseEntity<Object> post(@Valid  @RequestBody TestRequest request) {
        return ResponseEntity.ok(CommonResponse.create(request));
    }

    @GetMapping("/api/v1/test2")
    public boolean test2() {

        return customRepository.test("test");
    }

}
