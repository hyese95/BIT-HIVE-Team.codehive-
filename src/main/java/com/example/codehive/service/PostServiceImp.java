package com.example.codehive.service;


import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import com.example.codehive.entity.User;
import com.example.codehive.repository.CommentRepository;
import com.example.codehive.repository.PostRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {
    @Autowired
    PostRepository postRepository;
    UserService userService;
    EntityManager entityManager;
    CommentRepository commentRepository;
    @Override
    public Page<Post> readByCategoryWithKeyword(String category, String keyword, String sortType, Pageable pageable) {
        Instant startDate = switch (sortType) {
            case "daily" -> Instant.now().minus(1, ChronoUnit.DAYS);
            case "weekly" -> Instant.now().minus(7, ChronoUnit.DAYS);
            case "monthly" -> Instant.now().minus(30, ChronoUnit.DAYS);
            default -> null;
        };
        System.out.println(sortType);
        System.out.println("startDate"+startDate);

        if (startDate == null) {

            return postRepository.findByCategoryWithKeyword(category, keyword, pageable);
        } else {
            System.out.println("시작@@@@@@@@@@@@@@@@@@@");
            System.out.println(postRepository.findByCategoryWithKeywordAndPeriod(category, keyword, startDate, pageable));
            return postRepository.findByCategoryWithKeywordAndPeriod(category, keyword, startDate, pageable);
        }

    }

    @Override
    public Page<Post> readByCategoryWithKeyword(String category, String keyword, Pageable pageable) {
        if (category.equals("all")) {
            category = "%";
        }
        return postRepository.findByCategoryWithKeyword(category, keyword, pageable);
    }

    @Override
    public Page<Post> readAllByCategory(Pageable pageable, String category) {
        Page<Post> posts;
        posts = postRepository.findAllByCategory(pageable, category);
        return posts;
    }

    @Override
    public Post getPostByPostId(int id) {
        Post posts;
        posts = postRepository.findPostById(id);
        return posts;
    }

    @Override
    @Transactional
    public void modifyPost(int postNo, String content) {
        Optional<Post> optionalPost = postRepository.findById(postNo);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setPostCont(content);  // 새로운 내용으로 업데이트
            entityManager.merge(post); // 변경 사항 저장
        } else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
    }

    @Override
    @Transactional
    public void deletePost(int postNo) {
        Optional<Post> optionalPost = postRepository.findById(postNo);
        if (optionalPost.isPresent()) {
            postRepository.deletePostByPostNo(postNo);
        }
        else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다");
        }
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post newPost = new Post();
        newPost.setPostCont(postDto.getPostCont());
//        newPost.setUser(user); // 현재 로그인한 사용자 정보 추가
        newPost.setPostCreatedAt(Instant.now());
        newPost.setCategory(postDto.getCategory());
        User user = userService.findAll().getFirst();
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        newPost.setUser(user);  // 실제 User 객체 설정
        newPost.setComment(new ArrayList<>());

        Post savedPost = postRepository.save(newPost);
        return new PostDto(savedPost);
    }

    @Override
    public Page<Post> readAll(PostDto postDto, Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> readByUserNo(Pageable pageable, int userNo) {
        return postRepository.findByUserNo(userNo, pageable);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<PostDto> readAllDtoByCategory(PostDto.PostSearchRequestDto request) {
        int page=request.getPage();
        int size=request.getSize();
        String category=request.getCategory();
        Pageable pageable=PageRequest.of(page,size,Sort.by("postCreatedAt").descending());
        List<Post> posts=postRepository.findAllByCategory(pageable,category);
        System.out.println(posts);
        int start=page*size;
        int end=Math.min(start+size,posts.size());
        if(start>end) start=end;
        List<PostDto> postDtoList=posts.subList(start,end).stream().map(post->{
            return new PostDto(post.getCategory());
        }).collect(Collectors.toList());
        return new PageImpl<>(postDtoList,PageRequest.of(page,size),posts.size());
    }
}
