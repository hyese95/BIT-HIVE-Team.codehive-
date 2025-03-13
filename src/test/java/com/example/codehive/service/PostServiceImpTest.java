package com.example.codehive.service;

import com.example.codehive.entity.Post;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class PostServiceImpTest {
    @Autowired
    PostService postService;
    @Test
    @Transactional
    void readAllByCategory() {
        Pageable pageable = PageRequest.of(0, 10);
        Page <Post> postPage=(postService.readAllByCategory(pageable,"free"));
        System.out.println(postPage.getContent());
    }
    @Test
    @Transactional
    void postLikeCount() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> postPage=(postService.readAllByCategory(pageable,"free"));
        System.out.println(postPage);
    }

    @Test
    @Transactional
    void getPostByPostId() {

       Post post=postService.getPostByPostId(1)
;        System.out.println(post);
    }
}