package com.example.codehive.service;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.dto.CommentLikeDto;
import com.example.codehive.entity.Comment;
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

    @Override
    public CommentLikeDto.LikeStatusResponse getLikeStatus(Integer userNo, Integer commentNo) {
        CommentLikeId commentLikeId = new CommentLikeId();
        commentLikeId.setUserNo(userNo);
        commentLikeId.setCommentNo(commentNo);
//        commentLikeId 값 저장된 것 불러오기
        return commentLikeRepository.findById(commentLikeId)
                .map(cl -> new CommentLikeDto.LikeStatusResponse(cl.getLikeType()))
                .orElse(new CommentLikeDto.LikeStatusResponse(null));
    }

    @Override
    public void setLike(Integer userNo, Integer commentNo, Boolean likeType) {
        CommentLikeId id = new CommentLikeId();
        id.setUserNo(userNo);
        id.setCommentNo(commentNo);
        Optional<CommentLike> existing = commentLikeRepository.findById(id);
        //이미 존재하는 좋아요는 likeType 을 바꾼다.
        if (existing.isPresent()) {
            CommentLike like = existing.get();
            like.setLikeType(likeType);
            commentLikeRepository.save(like);
        } else {
//            새 좋아요를 누르는 유저를 저장한다.
            Comment comment = commentRepository.findById(commentNo)
                    .orElseThrow(() -> new RuntimeException("댓글 없음"));
            CommentLike like = new CommentLike();
            like.setId(id);
            like.setComment(comment);
            like.setLikeType(likeType);
            commentLikeRepository.save(like);
        }
    }

    @Override
    public void cancelLike(Integer userNo, Integer commentNo) {
        CommentLikeId id = new CommentLikeId();
        id.setUserNo(userNo);
        id.setCommentNo(commentNo);
        commentLikeRepository.deleteById(id);
    }
    //좋아요, 싫어요 취소
}

