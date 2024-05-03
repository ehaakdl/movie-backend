package com.movie.backend.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.backend.model.entity.User;
import com.movie.backend.repository.CustomRepository;
import com.movie.backend.repository.UserRepository;

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

    @GetMapping("/api/v1/test2")
    public boolean test2() {

        return customRepository.test("test");
    }

}
