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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    PostRepository postRepository;

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
}
