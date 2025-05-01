package com.example.codehive.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilTest {
    @Autowired
    JwtUtil jwtUtil;

    @Test
    void generateToken() {
        System.out.println(jwtUtil.generateToken("user1"));
    }

    @Test
    void validateToken() {
        System.out.println(jwtUtil.validateToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTc0NjEzODA2NX0.-SgzwZK6DmVqspziAGyfS0Vc6yMiGSJbAIlUibomPO5qhpi8T8OaPVlkcejpVV3YU9gZrWW1ZXRv0aE_UVDRwA"));
    }
}