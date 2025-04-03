package com.example.codehive.service;

import com.example.codehive.dto.PostDto;
import com.example.codehive.dto.PostLikeDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import com.example.codehive.entity.PostLikeId;
import com.example.codehive.entity.User;
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
    public PostDto toggleLike(Integer userNo, Integer postNo, Boolean likeType) {
        Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없음"));
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));
        Optional<PostLike> existingLike = postLikeRepository.findByPostAndUser(post, user);
        if (existingLike.isPresent()) {
            PostLike postLike = existingLike.get();
            if (postLike.getLikeType()==likeType) {
                // 같은 값이면 삭제
                postLikeRepository.delete(postLike);
            } else {
                // 다른 값이면 변경
                postLike.setLikeType(likeType);
                postLikeRepository.save(postLike);
            }
        } else {
            // 새로 추가
            PostLike newLike = new PostLike();
            newLike.setPost(post);
            newLike.setUser(user);
            newLike.setLikeType(likeType);
            postLikeRepository.save(newLike);
        }
        int likeCount = postLikeRepository.countByPostAndLikeType(post, true);
        int dislikeCount = postLikeRepository.countByPostAndLikeType(post, false);

        return new PostDto(post.getId(), likeCount, dislikeCount);
    }

    @Override
    public PostLikeDto getPostLikeById(Integer postNo) {
        return postLikeRepository.getPostLikeAndDislikeCount(postNo);
    }
}
