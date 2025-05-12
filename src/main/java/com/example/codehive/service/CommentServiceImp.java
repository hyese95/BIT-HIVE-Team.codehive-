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
        List<Comment> comments = commentRepository.findByPostNo(postNo);
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
    public CommentAndUserLikeDto toggleCommentLike(int commentNo, int userNo, Boolean likeType) {
        CommentLikeId id = new CommentLikeId();
        id.setCommentNo(commentNo);
        id.setUserNo(userNo);
        Optional<CommentLike> existing = commentLikeRepository.findById(id);
        if (existing.isPresent()) {
            // 토글 해제 -> 원래 있던 좋아요 삭제
            if (existing.get().getLikeType().equals(likeType)) {
                commentLikeRepository.deleteById(id);
                commentLikeRepository.flush();//강제 레포지토리 초기화 후 변한 데이터 반영
                // 안할시 delete가 실행되기 전으로 인식하여 함수 구조가 실행되지 않는다.
                entityManager.clear();
            } else {
                // 타입 변경 (like ↔ dislike)
               CommentLike commentLike=existing.get();
               commentLike.setLikeType(likeType);
               commentLikeRepository.save(commentLike);
            }
        } else {
            // 새로 추가
            CommentLike newLike = new CommentLike();
            newLike.setId(id);
            newLike.setLikeType(likeType);
            commentLikeRepository.save(newLike);
        }
        // 토글 후 최신 상태 반환
        // 댓글 번호로 - 댓글 조회 - 그걸로 CommentDto 조회, 그 CommentDto를 userLike와 합쳐서
        // 새로 정의된 commentAndUserLikeDto를 return 한다!(새로 정의된 Dto는 오로지 userLike 만을 추가로 가져오는게 목적)
        // 기존 정의된 Dto는 이미 쓰는 객체들이 많고, 이 Dto는 오로지 그 데이터만을 추가하기 위함
        Comment comment = commentRepository.findById(commentNo);
        CommentDto commentDto = new CommentDto(comment);
        Boolean userLike = commentLikeRepository.findById(id)
                .map(CommentLike::getLikeType)
                .orElse(null);
        CommentAndUserLikeDto commentAndUserLikeDto=new CommentAndUserLikeDto(commentDto,userLike,null);
        return commentAndUserLikeDto;
    }

}

