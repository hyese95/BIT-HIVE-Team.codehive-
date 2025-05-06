package com.example.codehive.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentLikeServiceImpTest {
    @Autowired
    CommentLikeService commentLikeService;

    @Test
    @Transactional
    void setCommentLike() {
        int loginUserNo=1;
        int commentNo=13;
        Boolean likeType=true;
        commentLikeService.setCommentLike(loginUserNo,commentNo,likeType);
    }

    @Test
    void removeLike() {
    }
}