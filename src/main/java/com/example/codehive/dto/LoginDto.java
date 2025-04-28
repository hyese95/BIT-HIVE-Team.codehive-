package com.example.codehive.dto;

import com.example.codehive.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    private String id;
    private String pw;

    private String jwt;
    private User user;
}
