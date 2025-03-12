package com.example.codehive.service;


import com.example.codehive.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<Post>findByCategoryWithKeyword(String category,String keyword);
    int getLikeSum(Post post);
    int getDislikeSum(Post post);
    Page<Post> readAllByCategory(Pageable pageable, String category);
    Post getPostByPostId(int id);
}
