package com.example.codehive.service;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.repository.CommentLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentLikeServiceImp implements CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;
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
}
