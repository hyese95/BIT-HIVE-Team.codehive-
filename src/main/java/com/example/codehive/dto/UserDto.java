package com.example.codehive.dto;

import com.example.codehive.entity.User;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String userId;
    private String nickname;
    private String email;
    private String phone;
    private String profileImgUrl;
    private String selfIntroduction;
    private Instant createdAt;
    private String name;
    private String nationality;
    private String gender;
    private LocalDate birthDate;
    private String theme;

    private Integer followerCount = 0;
    private Integer followingCount = 0;
    private Integer postCount = 0;

    public static UserDto from(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserId(user.getUserId());
        dto.setNickname(user.getNickname());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setProfileImgUrl(user.getProfileImgUrl());
        dto.setSelfIntroduction(user.getSelfIntroduction());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setName(user.getName());
        dto.setNationality(user.getNationality());
        dto.setGender(user.getGender());
        dto.setBirthDate(user.getBirthDate());
        dto.setTheme(user.getTheme());
        return dto;
    }

    public UserDto withStats(Integer postCount, Integer followerCount, Integer followingCount) {
        this.postCount = postCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        return this;
    }
}