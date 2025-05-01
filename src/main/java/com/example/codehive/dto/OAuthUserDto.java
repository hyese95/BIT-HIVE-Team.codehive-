package com.example.codehive.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class OAuthUserDto {
    @NotBlank
    private String email;
    private String name;
    private String picture;

    @NotBlank
    private String oauth;
}
