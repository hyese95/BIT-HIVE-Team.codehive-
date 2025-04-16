package com.example.codehive.service;


import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface PostService {
    Page<Post>readByCategoryWithKeyword(String category,String keyword, Pageable pageable);
    Page<Post> readByCategoryWithKeyword(String category, String keyword, String sortType, Pageable pageable);
    Page<Post> readAllByCategory(Pageable pageable, String category);
    Post getPostByPostId(int id);
    void modifyPost(int postNo, String content);
    void deletePost(int postNo);
    PostDto createPost(PostDto postDto);
    Page<Post> readAll(PostDto postDto, Pageable pageable);
    Page<Post>readByUserNo(Pageable pageable, int userNo);
    Page<Post> findAll(Pageable pageable);
    List<Post> getAllPosts();
    Page<PostDto> readAllDtoByCategory(PostDto.PostSearchRequestDto request);
}
