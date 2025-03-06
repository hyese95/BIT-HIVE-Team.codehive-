package com.example.codehive.service;

import com.example.codehive.entity.Post;
import com.example.codehive.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {
    private PostRepository postRepository;
    @Override
    public Page<Post> ReadAllByCategory(Pageable pageable,String category) {
        Page<Post> posts;
        posts = postRepository.findAllByCategory(pageable,category);
        return posts;
    }
}
