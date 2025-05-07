package com.example.codehive.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostLikeServiceImpTest {
    @Autowired
    PostLikeService postLikeService;
    @Test
    void getPostLike() {
        System.out.println(postLikeService.GetPostLike(1,5));
    }

    @Test
    void setPostLike() {
        System.out.println(postLikeService.setPostLike(1,5,true));
    }
}