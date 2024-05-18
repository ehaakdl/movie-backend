package com.movie.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ThymeleafController {

    @GetMapping("/api/v1/tt")
    public String test(Model model) {
        model.addAttribute("loginId", "testId");

        return "test";
    }
}
