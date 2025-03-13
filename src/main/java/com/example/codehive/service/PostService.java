package com.example.codehive.service;


import com.example.codehive.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface PostService {
    Page<Post>readByCategoryWithKeyword(String category,String keyword, Pageable pageable);
    Page<Post> readAllByCategory(Pageable pageable, String category);
    Post getPostByPostId(int id);
}
