package com.mose.movie.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mose.movie.common.EnvironmentKey;
import com.mose.movie.common.FrontUrl;
import com.mose.movie.common.eIgnoreSecurityPath;
import com.mose.movie.common.token.TokenCreator;
import com.mose.movie.common.token.TokenUtils;
import com.mose.movie.filter.ExceptionHandlerFilter;
import com.mose.movie.filter.JwtTokenAuthenticationFilter;
import com.mose.movie.handler.LoginSuccessHandler;
import com.mose.movie.service.WebClientService;
import com.mose.movie.service.security.AuthenticationFailHandlerImpl;
import com.mose.movie.service.security.AuthenticationSuccessHandlerImpl;
import com.mose.movie.service.security.JwtTokenProvider;
import com.mose.movie.service.security.LogoutHandlerImpl;
import com.mose.movie.service.security.LogoutSuccessHandlerImpl;
import com.mose.movie.service.security.Oauth2UserService;
import com.mose.movie.service.security.UserDetailsServiceImpl;
import com.mose.movie.service.user.UserService;
import com.gora.common.repository.TokenRepository;
import com.gora.common.repository.UserRepository;
import com.gora.common.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final LoginSuccessHandler loginSuccessHandler;
    private final TokenRepository tokenRepository;
    private final Environment environment;
    private final UserRepository userRepository;
    private final TokenUtils tokenUtils;
    private final LogoutSuccessHandlerImpl logoutSuccessHandler;
    private final MessageSource messageSource;
    private final ObjectMapper objectMapper;
    private final UserRoleRepository userRoleRepository;
    private final LogoutHandlerImpl logoutHandlerImpl;
    private final TokenCreator tokenCreator;
    private final WebClientService webClientService;
    private final UserService userService;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.PUT);
        configuration.addAllowedMethod(HttpMethod.DELETE);
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final String loginPageUrl = environment.getProperty(EnvironmentKey.APP_FRONT_URL) + FrontUrl.LOGIN;
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(eIgnoreSecurityPath.urls).permitAll()
                        .anyRequest()
                        .authenticated())
                .logout(logout -> logout
                        .addLogoutHandler(logoutHandlerImpl)
                        .logoutUrl("/api/v1/logout")
                        .logoutSuccessHandler(logoutSuccessHandler))
                .oauth2Login(login -> login
                        .authorizationEndpoint(t -> t.baseUri("/oauth2/authorize"))
                        // oauth2Login만 하면 필요없는데 추가설정 들어가니까 이 url 없으면 안됨
                        .loginPage(loginPageUrl)
                        .successHandler(new AuthenticationSuccessHandlerImpl(loginSuccessHandler))
                        .failureHandler(new AuthenticationFailHandlerImpl())
                        .userInfoEndpoint(
                                t -> t.userService(oauth2UserService())));

        // 필터 순서 중요 에러 필터는 에러 예상되는 필터보다 먼저 호출되어야한다.
        http.addFilterBefore(new ExceptionHandlerFilter(messageSource, objectMapper),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtTokenAuthenticationFilter(jwtTokenProvider(),
                eIgnoreSecurityPath.urls, tokenUtils, tokenCreator), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationFailureHandler authenticationFailHandler() {
        return new AuthenticationFailHandlerImpl();
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandlerImpl(loginSuccessHandler);
    }

    @Bean
    Oauth2UserService oauth2UserService() {
        return new Oauth2UserService(userService,
                webClientService, objectMapper);
    }

    @Bean
    UserDetailsServiceImpl UserDetailsServiceImpl() {
        return new UserDetailsServiceImpl(userRepository, userRoleRepository);
    }

    @Bean
    JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(UserDetailsServiceImpl(), tokenRepository);
    }
}
