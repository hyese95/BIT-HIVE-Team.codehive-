package com.example.codehive.service;


import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import com.example.codehive.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    PostRepository postRepository;

    @Override
    public List<Post> findByCategoryWithKeyword(String category, String keyword) {
        if (category.equals("all")) {
            return postRepository.findByKeyword(keyword);
        }

        return postRepository.findByCategoryWithKeyword(category, keyword);
    }

    @Override
    public int getLikeSum(Post post) {
        int likeSum = 0;
        List<PostLike> postLikes = postRepository.findLikesByPostNo(post.getId());
        for (PostLike postLike : postLikes) {
            if(postLike.getLikeType().equals(true)) {
                likeSum+=1;
            }
        }



        return likeSum;
    }

    @Override
    public int getDislikeSum(Post post) {
        int dislikeSum = 0;
        List<PostLike> postLikes = postRepository.findLikesByPostNo(post.getId());
        for (PostLike postLike : postLikes) {
            if(postLike.getLikeType().equals(false)) {
                dislikeSum+=1;
            }
        }



        return dislikeSum;
    }
}
