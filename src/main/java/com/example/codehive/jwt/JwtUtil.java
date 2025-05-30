package com.example.codehive.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@AllArgsConstructor
public class JwtUtil {

    private final String SECRET_KEY = "안녕하세요!이건Jwt비밀키입니다길게하세요더길게길게길게길게길게길게하세요";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private final long EXPIRATION = 1000 * 60 * 60;

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) throws JwtException {
        try {
            Jwts.parser().verifyWith(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            throw new JwtException("JWT 유효성 실패: " + e.getMessage());
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}