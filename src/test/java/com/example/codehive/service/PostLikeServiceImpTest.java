package com.example.codehive.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PostLikeServiceImpTest {
    @Autowired
    PostLikeService postLikeService;
    @Test
    @Transactional
    void getPostLike() {
        System.out.println(postLikeService.getPostLike(1,5).toString());
    }

    @Test
    void setPostLike() {
        System.out.println(postLikeService.setPostLike(1,5,true));
        System.out.println(postLikeService.setPostLike(1,5,true));
        System.out.println(postLikeService.setPostLike(1,5,true));
        System.out.println(postLikeService.setPostLike(1,5,true));
        System.out.println(postLikeService.setPostLike(1,5,true));
        System.out.println(postLikeService.setPostLike(1,5,true));
    }

    @Test
    @Transactional
    void togglePostLike() {
        System.out.println(postLikeService.togglePostLike(1,5,true));
        System.out.println(postLikeService.togglePostLike(1,5,true));
        System.out.println(postLikeService.togglePostLike(1,5,true));
        System.out.println(postLikeService.togglePostLike(1,5,false));
        System.out.println(postLikeService.togglePostLike(1,5,false));
        System.out.println(postLikeService.togglePostLike(1,5,false));
        System.out.println(postLikeService.togglePostLike(1,5,false));
        System.out.println(postLikeService.togglePostLike(1,5,false));
        System.out.println(postLikeService.togglePostLike(1,5,true));
    }
}