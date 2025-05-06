package com.example.codehive.service;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.dto.CommentLikeDto;
import com.example.codehive.entity.CommentLike;
import com.example.codehive.entity.CommentLikeId;
import com.example.codehive.repository.CommentLikeRepository;
import com.example.codehive.repository.CommentRepository;
import com.example.codehive.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
    @Transactional
    public CommentLike GetCommentLike(Integer userNo, Integer commentNo){
        CommentLikeId commentLikeId = new CommentLikeId();
        commentLikeId.setUserNo(userNo);
        commentLikeId.setCommentNo(commentNo);
//        commentLikeId 값 저장된 것 불러오기
        Optional<CommentLike> existingLike = commentLikeRepository.findCommentLikeById(commentLikeId);
        if (existingLike.isEmpty()) {
            return null;
        }
        return existingLike.get();
    }

    @Override
    public ResponseEntity<?> setCommentLike(Integer userNo, Integer commentNo, Boolean likeType) {
    CommentLikeId id = new CommentLikeId();
    id.setUserNo(userNo);
    id.setCommentNo(commentNo);
    Optional<CommentLike> existingLike = commentLikeRepository.findCommentLikeById(id);
//        존재하면 삭제, EmbeddedId 구조 에서는 그게 좋다
        existingLike.ifPresent(like -> commentLikeRepository.delete(like));
        CommentLike commentLike = commentLikeRepository.findById(id).orElse(null);
        if (commentLike != null) {
            commentLikeRepository.delete(commentLike);
        }
        CommentLike newLike = new CommentLike();
        newLike.setLikeType(likeType);
        newLike.setId(id);
        commentLikeRepository.save(newLike);
    return ResponseEntity.ok(newLike);
        }

    @Override
    public ResponseEntity<?> deleteCommentLike(Integer userNo, Integer commentNo) {
        CommentLikeId id = new CommentLikeId();
        id.setUserNo(userNo);
        id.setCommentNo(commentNo);
        commentLikeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

