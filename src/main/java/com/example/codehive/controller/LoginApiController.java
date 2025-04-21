package com.example.codehive.controller;

import com.example.codehive.dto.LoginDto;
import com.example.codehive.jwt.JwtUtil;
import com.example.codehive.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class LoginApiController {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/jwt/login.do")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getId());

        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        loginDto.setJwt(jwt); // LoginDto에 jwt 추가
        return ResponseEntity.ok(loginDto);
    }
}