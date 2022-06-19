package com.mose.movie.controller;

import com.mose.movie.etc.define.Urls;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.mose.movie.config.SecurityConfig.CSRF_TOKEN_SESSION_ATTR_NM;

@RestController
public class CsrfTokenController {
    @GetMapping(Urls.CSRF_TOKEN)
    public @ResponseBody CsrfToken csrfToken(HttpSession session){
        CsrfToken csrfToken = (CsrfToken) session.getAttribute(CSRF_TOKEN_SESSION_ATTR_NM);
        return csrfToken;
    }
}
