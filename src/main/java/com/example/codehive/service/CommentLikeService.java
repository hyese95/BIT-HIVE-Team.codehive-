package com.example.codehive.service;

import com.example.codehive.dto.CommentLikeCountDTO;

import java.util.List;

public interface CommentLikeService {
    List<CommentLikeCountDTO> getLikesAndDislikesCount();
    }
