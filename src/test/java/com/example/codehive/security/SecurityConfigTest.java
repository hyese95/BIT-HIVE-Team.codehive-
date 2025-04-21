package com.example.codehive.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    @Test
    void passwordEncoder() {
        System.out.println(BCrypt.hashpw("pass123", BCrypt.gensalt()));
        // $2a$10$WT9ZAkzs3OAC5RCXhTMwPu4TutRlU1SGUFXyb95C1oM/ya/6VnAWe
    }
}