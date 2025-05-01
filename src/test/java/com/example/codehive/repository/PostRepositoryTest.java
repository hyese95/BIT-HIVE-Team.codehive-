package com.example.codehive.repository;

import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import com.example.codehive.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    Pageable pageable = PageRequest.of(0, 10);
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void findById() {
        System.out.println(postRepository.findById(1).orElse(null));
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

    @Test
    @Transactional
    void findPostById() {
        System.out.println(postRepository.findPostById(1));
    }

    @Test
    @Transactional
    void findPostListById() {
        System.out.println(postRepository.findPostListById(1));
    }

    @Test
    void findPostByCategoryAndSort() {
        Pageable pageable= PageRequest.of(0, 10);
        System.out.println(postRepository.findPostByCategoryAndSort("free",pageable));
    }

    @Test
    @Transactional
    void createPost() {
        Post post = new Post();
        User user = new User();
        user=userRepository.findById(1).orElse(null);
        post.setUser(user);
        post.setCategory("free");
        post.setPostCont("자유의 몸이 되게 해줘");
        post.setPostCreatedAt(LocalDateTime.now());
        post.setImgUrl(null);
        postRepository.save(post);
        PostDto postDto = new PostDto(post);
        postDto.setUserNo(user.getId());
        System.out.println(user);
        System.out.println(postDto);
        System.out.println(postService.createPost(postDto,post.getUser().getNickname()));
    }
}