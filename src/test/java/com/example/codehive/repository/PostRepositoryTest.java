package com.example.codehive.repository;

import com.example.codehive.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    Pageable pageable = PageRequest.of(0, 10);
    @Test
    @Transactional
    void findById() {
        System.out.println(postRepository.findById(1L));
    }


    @Test
    @Transactional
    void findAllByCategory() {
        System.out.println(postRepository.findAllByCategory(Pageable.ofSize(1), "free").getContent());
    }

    void findByKeyword() {
        List<Post> posts = postRepository.findByKeyword("시장");
        System.out.println(posts);
    }

}