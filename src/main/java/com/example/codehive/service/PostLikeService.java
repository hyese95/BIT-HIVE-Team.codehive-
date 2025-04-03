package com.example.codehive.service;

import com.example.codehive.dto.PostDto;
import com.example.codehive.dto.PostLikeDto;

import java.util.Map;

public interface PostLikeService {
    Map<Integer, PostLikeDto> countPostLikes();
    public PostDto toggleLike(Integer userNo, Integer commentNo, Boolean likeType);
    public PostLikeDto getPostLikeById(Integer postNo);
}
