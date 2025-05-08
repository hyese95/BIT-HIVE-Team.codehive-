package com.example.codehive.security;

import com.example.codehive.jwt.JwtLoginFilter;
import com.example.codehive.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtLoginFilter jwtLoginFilter;     // 로그인 처리 필터
    private final JwtTokenFilter jwtTokenFilter;     // JWT 인증 필터

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "index.html",
                                "/assets/**",
                                "/images/**",
                                "/",                     // 홈
                                "/community/**",
                                "/trade/**",
                                "/news/**",
                                "/user/jwt/login.do",    // 로그인 요청
                                "/user/jwt/signup.do", // 회원가입 요철
                                "/favicon.ico",
                                "/static/**",           //  정적파일
                                "/img/**",              // 전부 허용
                                "/css/**",              // CSS 전체 허용
                                "/js/**",                // JS 전체 허용
                                // 중복체크 용
                                "/user/check-userid",
                                "/user/check-nickname",
                                "/user/check-email",
                                "/user/oauth/**",
                                "/user/myinfo",
                                "/api/**",// 개발편의성을 위해 api 주소는 임시로 허용함
                                "/trade/api/**"// 개발편의성을 위해 api 주소는 임시로 허용함
                        ).permitAll()
                        .anyRequest().authenticated()  // 그 외 요청은 인증 필요
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterAt(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class) // 로그인 필터 (아이디/비번 → JWT 발급)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class) // JWT 검증 필터
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화용
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}