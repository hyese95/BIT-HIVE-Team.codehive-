package com.example.codehive.controller;

import com.example.codehive.dto.LoginDto;
import com.example.codehive.entity.User;
import com.example.codehive.jwt.JwtUtil;
import com.example.codehive.security.CustomUserDetailsService;
import com.example.codehive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RequiredArgsConstructor
public class LoginApiController {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/jwt/login.do")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getId());

        if (!passwordEncoder.matches(loginDto.getPw(), userDetails.getPassword())) {
            System.out.println("비밀번호 불일치");
            return ResponseEntity.status(403).build(); // 비밀번호 틀림
        }

        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        loginDto.setJwt(jwt); // LoginDto에 jwt 추가

        // 유저정보 담기
        Optional<User> loginUserOpt = userService.readByUserId(userDetails.getUsername());
        if (loginUserOpt.isPresent()) {
            loginDto.setUser(loginUserOpt.get());
        }
        return ResponseEntity.ok(loginDto);
    }

    @PostMapping("/jwt/signup.do")
    public ResponseEntity<LoginDto> signupAction(@RequestBody User user) {
        try {
            // 회원가입
            userService.register(user);

            // 가입 후 자동 로그인 처리
            Optional<User> loginUserOpt = userService.readByUserId(user.getUserId());
            if (loginUserOpt.isPresent()) {
                String jwt = jwtUtil.generateToken(user.getUserId());
                LoginDto loginDto = new LoginDto();
                loginDto.setJwt(jwt);
                loginDto.setUser(loginUserOpt.get());
                return ResponseEntity.ok(loginDto);
            }

            return ResponseEntity.status(403).build(); // 로그인 실패
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(409).build(); // 중복 아이디
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build(); // 서버 에러
        }
    }
}