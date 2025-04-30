package com.example.codehive.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilTest {
    @Autowired
    JwtUtil jwtUtil;

    @Test
    @Transactional
    void generateToken() {
        String token = jwtUtil.generateToken("admin1");
        System.out.println(token);
    }

    @Test
    @Transactional
    void getUsername() {
        String userName=jwtUtil.getUsername("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE3NDU5Mzg4Mjh9.JCazptlHLdGe4ABJA1HHz3GNwWwmeqd_ZePxPdtEyygm-0tNpHcN5AEFJHyOWXQiV39eKj05OEle70v0Nfzk6w");
        System.out.println(userName);
    }

    @Test
    void validateToken() {
    }
}