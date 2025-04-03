package com.example.codehive.service;

import com.example.codehive.dto.PostLikeDto;

import java.util.Map;

public interface PostLikeService {
    Map<Integer, PostLikeDto> countPostLikes();
    public PostLikeDto toggleLike(Integer userNo, Integer commentNo, Boolean likeType);
}
