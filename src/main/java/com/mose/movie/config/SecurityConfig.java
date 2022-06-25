package com.mose.movie.config;

import com.mose.movie.etc.define.Urls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .csrfTokenRepository(sessionCsrfRepository())
                .ignoringAntMatchers(Urls.CSRF_TOKEN, "/h2-console/**")
                .and()
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .anyRequest().permitAll()
                )
                .headers()
//                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }

    public static final String CSRF_TOKEN_SESSION_ATTR_NM = "CSRF_TOKEN";

    @Bean
    HttpSessionCsrfTokenRepository sessionCsrfRepository() {
        HttpSessionCsrfTokenRepository csrfRepository = new HttpSessionCsrfTokenRepository();
        csrfRepository.setSessionAttributeName(CSRF_TOKEN_SESSION_ATTR_NM);
        return csrfRepository;
    }
}