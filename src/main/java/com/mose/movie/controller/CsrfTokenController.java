package com.mose.movie.controller;

import com.mose.movie.etc.define.Urls;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"CSRF TOKEN 자원 관리"})
@RestController
@RequiredArgsConstructor
public class CsrfTokenController {

    private final HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository;

    @ApiOperation(value = "CSRF TOKEN 발급해 준다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "발급 성공", response = CsrfToken.class)
    })
    @GetMapping(Urls.CSRF_TOKEN)
    public @ResponseBody CsrfToken csrfToken(HttpServletRequest request){
        return httpSessionCsrfTokenRepository.loadToken(request);
    }
}
