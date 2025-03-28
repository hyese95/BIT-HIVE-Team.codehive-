package com.example.codehive.dto;

import java.time.Instant;

public class FollowDto {

    /**
     * 팔로워 목록 조회 (나를 팔로우하는 사람들)
     */
    public record Follower(
            Integer followerUserNo,
            String nickname,
            String profileImgUrl,
            String name,
            Instant followingDate
    ) {
    }

    /**
     * 팔로잉 목록 조회(내가 팔로우하는 사람들)
     */
    public record Following(
            Integer followingUserNo,
            String nickname,
            String profileImgUrl,
            String name,
            Instant followingDate
    ) {
    }




}