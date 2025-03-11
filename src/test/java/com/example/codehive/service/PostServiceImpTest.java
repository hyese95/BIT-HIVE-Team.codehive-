package com.example.codehive.service;

import com.example.codehive.entity.Post;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImpTest {
    @Autowired
    PostService postService;
    @Test
    @Transactional
    void readAllByCategory() {
        Pageable pageable = PageRequest.of(0, 10);
        Page <Post> postPage=(postService.ReadAllByCategory(pageable,"free"));
        System.out.println(postPage.getContent());
    }

    @Test
    @Transactional
    void findByCategoryWithKeyword() {
        Pageable pageable = PageRequest.of(0, 2);
        String category="%";
        String keyword="인증";
        Page<Post> postPage=postService.readByCategoryWithKeyword(category, keyword, pageable);
        System.out.println(postPage.getContent());
    }
}