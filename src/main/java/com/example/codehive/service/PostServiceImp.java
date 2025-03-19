package com.example.codehive.service;


import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import com.example.codehive.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    PostRepository postRepository;

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
    public void modifyPost(int postNo, String content) {
        Optional<Post> optionalPost = postRepository.findById(postNo);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setPostCont(content);  // 새로운 내용으로 업데이트
            postRepository.save(post);  // 변경 사항 저장
        } else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
    }

    @Override
    public void deletePost(int postNo) {
        Optional<Post> optionalPost = postRepository.findById(postNo);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postRepository.delete(post);
        }
        else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다");
        }
    }
}
