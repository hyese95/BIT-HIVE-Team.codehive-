package com.example.codehive.service;

import com.example.codehive.dto.PostDto;
import com.example.codehive.dto.PostLikeDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import com.example.codehive.entity.User;
import com.example.codehive.repository.PostLikeRepository;
import com.example.codehive.repository.PostRepository;
import com.example.codehive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
    public PostDto modifyLike(Integer userNo, Integer postNo, Boolean likeType) {
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
        if(likeType){
            postLikeRepository.countByPostAndLikeType(post, likeType);

        }
        return new PostDto(new Post());
    }

    @Override
    public PostLikeDto getPostLikeById(Integer postNo) {
        return postLikeRepository.getPostLikeAndDislikeCount(postNo);
    }

    @Override
    @Transactional
    public PostLike GetPostLike(Integer userNo, Integer postNo) {
        User user=userRepository.findById(userNo).orElseThrow();
        Post post=postRepository.findPostById(postNo);
        PostLike postLike=postLikeRepository.findByPostAndUser(post,user).orElse(null);
        return postLike;
    }

    @Override
    @Transactional
    public ResponseEntity<?> setPostLike(Integer userNo, Integer postNo, Boolean likeType) {
        PostLike postLike=new PostLike();
        Post post=postRepository.findPostById(postNo);
        postLike.setLikeType(likeType);
        postLike.setPostNo(postNo);
        postLike.setUserNo(userNo);
        postLike.setPost(post);
        User user=userRepository.findById(userNo).orElseThrow();
        Optional<PostLike> existingPostLike=postLikeRepository.findByPostAndUser(post,user);
        postLikeRepository.save(postLike);
//        이전에 일치하는게 있으면 삭제 절차 + 토글 구현을 위함 + 덮어 써서 수치를 늘리는걸 방지하기 위해서
        if(postLike==existingPostLike.orElse(null)){
            postLikeRepository.delete(existingPostLike.orElse(null));
            return ResponseEntity.ok(postLike);
        }
        return ResponseEntity.ok(postLike);
    }

    @Override
    @Transactional
    public PostLike CreatePostLike(Integer userNo, Integer postNo, Boolean likeType) {
        User user=userRepository.findById(userNo).orElseThrow();
        Post post=postRepository.findPostById(postNo);
        Optional<PostLike> existingPostLike=postLikeRepository.findByPostAndUser(post,user);
        if(existingPostLike.isPresent()){
            postLikeRepository.delete(existingPostLike.orElse(null));
        }
        PostLike postLike=new PostLike();
        postLike.setLikeType(likeType);
        postLike.setPostNo(postNo);
        postLike.setUserNo(userNo);
        postLike.setPost(post);
        postLike.setUser(user);
        postLikeRepository.save(postLike);
        return postLike;
    }

    @Override
    public ResponseEntity<?> DeletePostLike(Integer userNo, Integer postNo,Boolean likeType) {
        Post post=postRepository.findPostById(postNo);
        User user=userRepository.findById(userNo).orElseThrow();
        Optional<PostLike> existing = postLikeRepository.findByPostAndUser(post, user);

        Map<String, Object> response = new HashMap<>();
        response.put("userNo", userNo);
        response.put("postNo", postNo);

        if (existing.isPresent()) {
            postLikeRepository.delete(existing.get());
            response.put("likeType", null);
        } else {
            PostLike newLike = new PostLike();
            newLike.setPost(post);
            newLike.setUser(user);
            newLike.setLikeType(likeType);
            postLikeRepository.save(newLike);
            response.put("likeType", likeType);
        }

        return ResponseEntity.ok(response);

    }
}
