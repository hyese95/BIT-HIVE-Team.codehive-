package com.example.codehive.service;

import com.example.codehive.dto.PostDto;
import com.example.codehive.dto.PostLikeDto;
import com.example.codehive.entity.PostLike;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PostLikeService {
    Map<Integer, PostLikeDto> countPostLikes();
    public PostDto modifyLike(Integer userNo, Integer commentNo, Boolean likeType);
    public PostLikeDto getPostLikeById(Integer postNo);
    public PostLike GetPostLike(Integer userNo, Integer postNo);
    public ResponseEntity<?> setPostLike(Integer userNo, Integer postNo, Boolean likeType);
    public PostLike CreatePostLike(Integer userNo, Integer postNo, Boolean likeType);
    public ResponseEntity<?> DeletePostLike(Integer userNo, Integer postNo);
}
