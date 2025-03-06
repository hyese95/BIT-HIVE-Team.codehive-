package com.example.codehive.service;


import com.example.codehive.entity.Post;

import java.util.List;

public interface PostService {
    List<Post>findByCategoryWithKeyword(String category,String keyword);
    int getLikeSum(Post post);
    int getDislikeSum(Post post);


}
