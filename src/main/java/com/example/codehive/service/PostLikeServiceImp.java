package com.example.codehive.service;

import com.example.codehive.dto.PostLikeDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import com.example.codehive.entity.PostLikeId;
import com.example.codehive.repository.PostLikeRepository;
import com.example.codehive.repository.PostRepository;
import com.example.codehive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostLikeServiceImp implements PostLikeService{
    @Autowired
    PostLikeRepository postLikeRepository;
    PostRepository postRepository;
    UserRepository userRepository;
    @Override
    public Map<Integer, PostLikeDto> countPostLikes() {
        List<PostLikeDto> postLikeDto =postLikeRepository.getPostLikeAndDislike();
        return postLikeDto.stream()
                .collect(Collectors.toMap(PostLikeDto::getPostNo, dto -> dto));
    }

    @Override
    @Transactional
    public PostLikeDto toggleLike(Integer userNo, Integer postNo, Boolean likeType) {
        PostLikeId id = new PostLikeId();
        id.setUserNo(userNo);
        id.setPostNo(postNo);
        Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postNo));
        Optional<PostLike> existingLike = postLikeRepository.findById(id);
        if (existingLike.isPresent()) {
            postLikeRepository.delete(existingLike.get());
        } else {
            PostLike newLike = new PostLike();
            newLike.setUserNo(userNo);
            newLike.setPostNo(postNo);
            newLike.setLikeType(likeType);
            postLikeRepository.save(newLike);
        }
        int likeCount = postLikeRepository.countByPostAndLikeType(post, true);
        int dislikeCount = postLikeRepository.countByPostAndLikeType(post, false);
        return postLikeRepository.getPostLikeAndDislikeCount(postNo);
    }
}
