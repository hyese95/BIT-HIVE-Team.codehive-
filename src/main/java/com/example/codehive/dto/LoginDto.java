package com.example.codehive.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    private String id;
    private String pw;

    private String jwt;
}
