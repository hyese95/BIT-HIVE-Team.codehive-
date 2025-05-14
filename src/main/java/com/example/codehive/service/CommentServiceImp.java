package com.example.codehive.service;

import com.example.codehive.dto.CommentAndUserLikeDto;
import com.example.codehive.dto.CommentDto;
import com.example.codehive.entity.Comment;
import com.example.codehive.entity.CommentLike;
import com.example.codehive.entity.CommentLikeId;
import com.example.codehive.entity.Post;
import com.example.codehive.repository.CommentLikeRepository;
import com.example.codehive.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;
    private final CommentLikeRepository commentLikeRepository;
    @Autowired
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Comment readComment(int id) {
        Comment comment = commentRepository.findById(id);
        return comment;
    }

    @Override
    public Comment readChildComment(int parentNo) {
        Comment comment =commentRepository.findWithChildCommentById(parentNo);
        return comment;
    }

    @Override
    public List<Comment> readCommentOfPost(int postNo) {
        return List.of();
    }

    @Override
    public int getCommentCountByPostNo(int postNo) {
        return commentRepository.countByPostNo(postNo);
    }

    @Override
    public int getChildCommentCountByPostNoAndParentNo(int postNo, int parentNo) {
        return commentRepository.countChildCommentByPostNoAndParentNo(postNo,parentNo);
    }

    @Override
    public List<Comment>  readCommentByPostNo(int postNo) {
        return commentRepository.findCommentByPostNo(postNo);
    }

    @Override
    public List<Comment>  readCommentByPostNoAndParentNo(int postNo, Integer parentNo) {
        return commentRepository.findCommentContByPostNoAndParentNo(postNo,parentNo);
    }

    @Override
    @Transactional
    public void removeCommentByCommentNo(int commentNo) {
        commentRepository.deleteCommentByPostNo(commentNo);
    }

    @Override
    public int getReplyCount(int parentNo) {
        return commentRepository.countByParentNo(parentNo);
    }

    @Override
    @Transactional
    public void writeComments(Comment comment) {
        comment.setPostNo(comment.getPostNo());
        comment.setUserNo(comment.getUserNo());
        comment.setCommentCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void modifyComment(Comment comment) {
        Optional<Comment> commentOpt = commentRepository.findById(comment.getId());
        if (commentOpt.isPresent()) {
            comment.setPost(postService.getPostByPostId(commentOpt.get().getPostNo()));
            comment.setUserNo(commentOpt.get().getUserNo());
            comment.setCommentCreatedAt(commentOpt.get().getCommentCreatedAt());
            comment.setParentNo(commentOpt.get().getParentNo());
            comment.setCommentLikes(commentOpt.get().getCommentLikes());
            comment.setCommentCont(comment.getCommentCont());  // 새로운 내용으로 업데이트
            entityManager.merge(comment); // 변경 사항 저장
        } else {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }
    }

    @Override
    @Transactional
    public void writeChildComments(Comment comment) {
        comment.setPostNo(comment.getPostNo());
        comment.setParentNo(comment.getParentNo());
        comment.setCommentCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> readAll() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional
    public List<CommentDto> readCommentDtoByPostNo(int postNo) {
        List<Comment> comments = commentRepository.findCommentByPostNo(postNo);
        List<CommentDto> commentDtoList=new ArrayList<>();
        CommentDto commentDto;
        for (Comment comment : comments) {
            commentDto = new CommentDto(comment);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    @Override
    @Transactional
    public CommentDto modifyCommentDto(Comment comment) {
        CommentDto commentDto= new CommentDto(comment);
        return commentDto;
    }

    @Override
    @Transactional
    public List<CommentAndUserLikeDto> getCommentsWithUserLikeType(int postNo, Integer userNo) {
        List<Comment> comments = commentRepository.findByPostNoOrderByCommentCreatedAtDesc(postNo);
        List<Integer> commentIds = comments.stream()
                .map(Comment::getId)
                .collect(Collectors.toList());

        Map<Integer, Boolean> commentLikeMap;

        if (userNo != null) {
            List<CommentLike> userLikes = commentLikeRepository.findByIdUserNoAndIdCommentNoIn(userNo, commentIds);
            commentLikeMap = userLikes.stream()
                    .collect(Collectors.toMap(
                            cl -> cl.getId().getCommentNo(),
                            CommentLike::getLikeType
                    ));
        } else {
            commentLikeMap = new HashMap<>();
        }
        return comments.stream()
                .map(comment -> {
                    CommentDto commentDto = new CommentDto(comment);
                    Boolean userLikeType = commentLikeMap.get(comment.getId()); // null이면 무반응 처리
                    return new CommentAndUserLikeDto(commentDto, userLikeType,null);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentAndUserLikeDto.responseToggle toggleCommentLikeStatus(int commentNo, int userNo, Boolean userLikeType) {
        CommentLikeId id = new CommentLikeId();
        id.setCommentNo(commentNo);
        id.setUserNo(userNo);
        // 1. 기존 상태 확인
        Optional<CommentLike> existing = commentLikeRepository.findById(id);
        CommentLike userLike;
        if (existing.isPresent()) {
            CommentLike like = existing.get();
            // 같은 타입 누른 경우 → 취소
            if (Objects.equals(like.getLikeType(), userLikeType)) {
                commentLikeRepository.delete(like);
            } else {
                like.setLikeType(userLikeType);
                commentLikeRepository.save(like);
            }
        } else if (userLikeType != null) {
            // 처음 누르는 경우
            CommentLike newLike = new CommentLike();
            newLike.setId(id);
            newLike.setLikeType(userLikeType);
            commentLikeRepository.save(newLike);
        }

        // Comment와 User의 likeType을 합친 새로운 DTO 반환
//        -> 에러로 최소한의 변수만 받는 request 와 response
        // 여기부터는 CommentDto로부터 바로 응답 생성
        Comment comment = commentRepository.findById(commentNo);
        CommentDto commentDto = new CommentDto(comment);
        return new CommentAndUserLikeDto.responseToggle(
                commentDto.getLikeCount(),
                commentDto.getDislikeCount(),
                userLikeType
        );
    }


}

