package com.example.codehive.repository;

import com.example.codehive.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Transactional
    @Test
    void findByKeyword() {
        List<Post> posts = postRepository.findByKeyword("시장");
        System.out.println(posts);


    }
}