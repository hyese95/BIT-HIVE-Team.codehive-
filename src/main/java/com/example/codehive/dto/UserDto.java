package com.example.codehive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String userId;
    private String nickname;
    private String email;
    private String phone;
    private String profileImgUrl;
    private String selfIntroduction;


    private String name;
    private String nationality;
    private String gender;
    private LocalDate birthDate;


    private String theme;


    private Integer followerCount;     // 팔로워 수
    private Integer followingCount;    // 팔로잉 수
    private Integer postCount;         // 게시글 수


}