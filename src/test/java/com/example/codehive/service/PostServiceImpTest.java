package com.example.codehive.service;

import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImpTest {
    @Autowired
    PostService postService;
    @Autowired
    EntityManager entityManager;
    @Test
    @Transactional
    void readAllByCategory() {
        Pageable pageable = PageRequest.of(0, 10);
        Page <Post> postPage=(postService.readAllByCategory(pageable,"free"));
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

    @Test
    @Transactional
    void getPostByPostId() {

       Post post=postService.getPostByPostId(1)
;        System.out.println(post);
    }

    @Test
    @Transactional
    void modifyPost() {
        Post post=new Post();
        User user=entityManager.find(User.class, 1);
        Hibernate.initialize(user);
        post.setPostCont("비트코인 떡상해라");
        post.setId(1);
        post.setUser(user);
        post.setCategory("free");
        postService.modifyPost(post.getId(), post.getPostCont());
        System.out.println(post);
    }

    @Test
    @Transactional
    void readByUserNo() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> posts=postService.readByUserNo(pageable,1);
        System.out.println(posts.getContent());

    }

    @Test
    @Transactional
    void readAll() {
        Pageable pageable = PageRequest.of(0, 10);
        PostDto postDto=new PostDto();
        System.out.println(postService.readAll(postDto,pageable));
    }

    @Test
    @Transactional
    void readAllDtoByCategory() {
        PostDto.PostSearchRequestDto request = new PostDto.PostSearchRequestDto();
        request.setCategory("free");
        request.setPage(1);
        request.setSize(10);
       Page<PostDto> postDtoPage=postService.readAllDtoByCategory(request);
        System.out.println(postDtoPage.getContent());
    }
}