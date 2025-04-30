package com.example.codehive.service;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.entity.CommentLike;
import com.example.codehive.entity.CommentLikeId;
import com.example.codehive.repository.CommentLikeRepository;
import com.example.codehive.repository.CommentRepository;
import com.example.codehive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentLikeServiceImp implements CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    @Override
    public List<CommentLikeCountDTO> getLikesAndDislikesCount() {
        List<CommentLikeCountDTO> results = commentLikeRepository.countLikesAndDislikesByComment();
        List<CommentLikeCountDTO> commentLikeCounts = new ArrayList<>();

        for (CommentLikeCountDTO result : results) {
            Integer commentNo = result.getCommentNo();    // 댓글 번호
            Integer likeCount = result.getLikeCount();                // 좋아요 수
            Integer dislikeCount = result.getDislikeCount();             // 싫어요 수
            commentLikeCounts.add(new CommentLikeCountDTO(commentNo, likeCount, dislikeCount));
        }

        return commentLikeCounts;
    }

    @Override
    @Transactional
    public CommentLikeCountDTO toggleLike(Integer userNo, Integer commentNo, Boolean likeType) {
        CommentLikeId id = new CommentLikeId();
        id.setUserNo(userNo);
        id.setCommentNo(commentNo);
        Optional<CommentLike> existingLike = commentLikeRepository.findCommentLikeById(id);
        if (existingLike.isPresent()) {
            commentLikeRepository.delete(existingLike.get());
        } else {
            CommentLike newLike = new CommentLike();
            newLike.setId(id);
            newLike.setUserNo(userRepository.findById(userNo).orElseThrow());
            newLike.setCommentNo(commentRepository.findById(commentNo).orElseThrow());
            newLike.setLikeType(likeType);
            commentLikeRepository.save(newLike);
        }
        return commentLikeRepository.getCommentLikeCount(commentNo);
    }

    @Override
    public Map<Integer, CommentLikeCountDTO> countCommentLikes() {
        List<CommentLikeCountDTO> likeCounts = commentLikeRepository.countLikesAndDislikesByComment();
        return likeCounts.stream()
                .collect(Collectors.toMap(CommentLikeCountDTO::getCommentNo, dto -> dto));
    }
}

