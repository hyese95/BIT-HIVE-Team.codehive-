package com.example.codehive.service;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.dto.CommentLikeDto;

import java.util.List;
import java.util.Map;

public interface CommentLikeService {
    List<CommentLikeCountDTO> getLikesAndDislikesCount();
    CommentLikeCountDTO toggleLike(Integer userNo, Integer commentNo, Boolean likeType);
    Map<Integer, CommentLikeCountDTO> countCommentLikes();
    CommentLikeDto.LikeStatusResponse getLikeStatus(Integer userNo, Integer commentNo);
    public void setLike(Integer userNo,Integer commentNo, Boolean likeType);
    public void cancelLike(Integer userNo,Integer commentNo);
    }
