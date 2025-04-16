package com.example.codehive.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // 스프링 시큐리티 폼로그인 제거떄문에 주석처리
                // .formLogin(login -> login
                        // .loginPage("/login/login.do")
                        // .loginProcessingUrl("/login/login.do")
                        // .usernameParameter("id")
                        // .passwordParameter("pw")
                        // .defaultSuccessUrl("/", true)
                        // .permitAll()
                // )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login/login.do")
                        .permitAll()
                );

        return http.build();
    }
}