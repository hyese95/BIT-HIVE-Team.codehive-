package com.example.codehive.controller;

import com.example.codehive.dto.LoginDto;
import com.example.codehive.entity.User;
import com.example.codehive.jwt.JwtUtil;
import com.example.codehive.security.CustomUserDetailsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @GetMapping("/login/login.do")
    public String login() {
        return "login/login";
    }

    @PostMapping("/login/login.do")
    public String loginAction(@ModelAttribute LoginDto loginDto, HttpServletResponse response) {
        String userId = loginDto.getId();
        String password = loginDto.getPw();
        // DB에서 사용자 정보 조회
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getId());

        // 시큐리티에 인증 객체 설정
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                loginDto.getPw(),
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // JWT 생성 및 쿠키에 담기
        String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
        Cookie jwtCookie = new Cookie("jwt", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(60 * 30);
        response.addCookie(jwtCookie);

        return "redirect:/";
    }
}
