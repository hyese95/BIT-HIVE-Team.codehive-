package com.example.codehive.controller;

import com.example.codehive.dto.LoginDto;
import com.example.codehive.jwt.JwtUtil;
import com.example.codehive.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class LoginApiController {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/jwt/login.do")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getId());

        if (!passwordEncoder.matches(loginDto.getPw(), userDetails.getPassword())) {
            System.out.println("비밀번호 불일치");
            return ResponseEntity.status(403).build(); // 비밀번호 틀림
        }

        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        loginDto.setJwt(jwt); // LoginDto에 jwt 추가
        return ResponseEntity.ok(loginDto);
    }
}