package com.example.codehive.controller;

import com.example.codehive.dto.LoginDto;
import com.example.codehive.entity.User;
import com.example.codehive.jwt.JwtUtil;
import com.example.codehive.security.CustomUserDetails;
import com.example.codehive.security.CustomUserDetailsService;
import com.example.codehive.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
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
    // 중복 체크 API 3개 추가
    @GetMapping("/check-userid")
    public ResponseEntity<Map<String, Boolean>> checkUserId(@RequestParam String userId) {
        boolean exists = userService.existsByUserId(userId);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam String nickname) {
        boolean exists = userService.existsByNickname(nickname);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
        boolean exists = userService.existsByEmail(email);
        return ResponseEntity.ok(Map.of("exists", exists));
    }


    // 로컬에 로그인유저 안뜨게하고 새로고침마다 jwt 다시받아오게하기

    @GetMapping("/myinfo")
    public ResponseEntity<User> getMyInfo(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        if (token != null && jwtUtil.validateToken(token)) {
            String userId = jwtUtil.getUsername(token);
            Optional<User> userOpt = userService.readByUserId(userId);
            if (userOpt.isPresent()) {
                return ResponseEntity.ok(userOpt.get());
            } else {
                return ResponseEntity.status(404).build(); // 유저 없음
            }
        }
        return ResponseEntity.status(401).build(); // 토큰 없음 or 유효하지 않음
    }
}