package com.example.codehive.service;

import com.example.codehive.dto.CommentLikeCountDTO;

import java.util.List;
import java.util.Map;

public interface CommentLikeService {
    List<CommentLikeCountDTO> getLikesAndDislikesCount();
    CommentLikeCountDTO toggleLike(Integer userNo, Integer commentNo, Boolean likeType);
    Map<Integer, CommentLikeCountDTO> countCommentLikes();
    }
