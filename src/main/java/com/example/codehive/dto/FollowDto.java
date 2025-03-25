package com.example.codehive.dto;

import java.time.Instant;

public class FollowDto {

    /**
     * 팔로워 목록 조회용 DTO (나를 팔로우하는 사람들)
     */
    public record Follower(
            Integer followerUserNo,        // 팔로워 사용자 번호
            String nickname,               // 팔로워 닉네임
            String profileImgUrl,          // 팔로워 프로필 이미지
            String name,       // 팔로워 이름
            Instant followingDate          // 팔로우 일자
    ) {}

    /**
     * 팔로잉 목록 조회용 DTO (내가 팔로우하는 사람들)
     */
    public record Following(
            Integer followingUserNo,       // 팔로잉 사용자 번호
            String nickname,               // 팔로잉 닉네임
            String profileImgUrl,          // 팔로잉 프로필 이미지
            String name,       // 팔로잉 이름
            Instant followingDate          // 팔로우 일자
    ) {}

    /**
     * 팔로우 관계 생성/삭제용 DTO
     */
    public record FollowRequest(
            Integer followerUserNo,        // 팔로워 사용자 번호 (팔로우 하는 사람)
            Integer followingUserNo        // 팔로잉 사용자 번호 (팔로우 당하는 사람)
    ) {}

    /**
     * 팔로우 상태 확인용 DTO
     */
    public record FollowStatus(
            Integer userNo,                // 대상 사용자 번호
            boolean isFollowing,           // 내가 대상을 팔로우하는지
            boolean isFollower             // 대상이 나를 팔로우하는지
    ) {}

    /**
     * 팔로우 통계용 DTO
     */
    public record FollowStats(
            Integer userNo,                // 사용자 번호
            long followerCount,            // 팔로워 수
            long followingCount            // 팔로잉 수
    ) {}
}