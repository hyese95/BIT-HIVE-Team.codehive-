package com.example.codehive.service;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.entity.CommentLike;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CommentLikeService {
    List<CommentLikeCountDTO> getLikesAndDislikesCount();
    Map<Integer, CommentLikeCountDTO> countCommentLikes();
    public ResponseEntity<?> setCommentLike(Integer userNo, Integer commentNo, Boolean likeType);
    public ResponseEntity<?> deleteCommentLike(Integer userNo, Integer commentNo,Boolean likeType);
    }
