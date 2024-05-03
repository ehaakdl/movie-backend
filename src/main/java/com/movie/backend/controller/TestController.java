package com.movie.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/api/v1/test")
    public String test() {

        return System.getenv("DB_PORT");
    }

}
