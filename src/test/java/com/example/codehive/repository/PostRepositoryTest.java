package com.example.codehive.repository;

import com.example.codehive.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    Pageable pageable = PageRequest.of(0, 10);
    @Test
    @Transactional
    void findById() {
    }


    @Test
    void findAllByCategory() {
        System.out.println(postRepository.findByCategory("free"));
    }


    @Test
    @Transactional
    void findByUserNo() {

    }

    @Test
    void findByCategory() {
        pageable = PageRequest.of(0, 10);
        String category = "free";
        Page<Post> post = postRepository.findByCategory(category,pageable);
        System.out.println(post.getTotalPages());
        System.out.println(post);
    }
}