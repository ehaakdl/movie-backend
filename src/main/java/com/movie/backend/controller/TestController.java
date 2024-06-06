package com.movie.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.backend.model.request.TestRequest;
import com.movie.backend.repository.CustomRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@Transactional
@RequiredArgsConstructor
public class TestController {
    private final CustomRepository customRepository;
    
    @PostMapping("/api/v1/post")
    public ResponseEntity<Object> post(@Valid  @NotBlank @RequestParam String name) {
        return ResponseEntity.ok("e");
    }

    @PostMapping("/api/v1/post2")
    public ResponseEntity<Object> post2(@RequestBody @Valid TestRequest request) {
        return ResponseEntity.ok("e");
    }

    @GetMapping("/api/v1/test2")
    public boolean test2() {

        return customRepository.test("test");
    }

}
