package com.mose.movie.controller;

import com.mose.movie.etc.define.Urls;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CsrfTokenController {

    private final HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository;

    @GetMapping(Urls.CSRF_TOKEN)
    public @ResponseBody CsrfToken csrfToken(HttpServletRequest request){
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.loadToken(request);
        return csrfToken;
    }
}
