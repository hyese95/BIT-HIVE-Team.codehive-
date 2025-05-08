package com.example.codehive.service;

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
    public List<CommentDto.CommentDtoRequest> getCommentsWithLikes(int postNo, int userNo) {
        // 1. 댓글 목록 조회 (like/dislike 수는 entity 관계에서 계산)
        List<Comment> comments = commentRepository.findByPostNo(postNo);
        // 2. 댓글 ID 목록 추출
        List<Integer> commentIds = comments.stream()
                .map(Comment::getId)
                .collect(Collectors.toList());
        // 3. 로그인한 유저가 눌렀던 좋아요/싫어요 정보 조회
        List<CommentLike> userLikes = commentLikeRepository.findByIdUserNoAndIdCommentNoIn(userNo, commentIds);
        // 4. commentId -> userLikeType(Boolean) 매핑
        Map<Integer, Boolean> userLikeMap = userLikes.stream()
                .collect(Collectors.toMap(
                        cl -> cl.getId().getCommentNo(),
                        CommentLike::getLikeType
                ));
        // 5. 최종 DTO 조합
        return comments.stream()
                .map(comment -> {
                    int likeCount = (int) comment.getCommentLikes().stream().filter(l -> l.getLikeType()).count();
                    int dislikeCount = (int) comment.getCommentLikes().stream().filter(l -> !l.getLikeType()).count();

                    CommentDto.CommentDtoS dto = new CommentDto.CommentDtoS(
                            comment.getId(),
                            comment.getParentNo(),
                            comment.getCommentCont(),
                            likeCount,
                            dislikeCount
                    );
                    Boolean userLikeType = userLikeMap.get(comment.getId());
                    return new CommentDto.CommentDtoRequest(dto, userLikeType);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentDto.CommentDtoRequest toggleCommentLike(int commentNo, int userNo, Boolean likeType) {
        CommentLikeId id = new CommentLikeId();
        id.setCommentNo(commentNo);
        id.setUserNo(userNo);
        Optional<CommentLike> existing = commentLikeRepository.findById(id);
        if (existing.isPresent()) {
            // 토글 해제 -> 원래 있던 좋아요 삭제
            if (existing.get().getLikeType().equals(likeType)) {
                commentLikeRepository.deleteById(id);
            } else {
                // 타입 변경 (like ↔ dislike)
                existing.get().setLikeType(likeType);
            }
        } else {
            // 새로 추가
            CommentLike newLike = new CommentLike();
            newLike.setId(id);
            newLike.setLikeType(likeType);
            commentLikeRepository.save(newLike);
        }
        // 토글 후 최신 상태 반환
        Comment comment = commentRepository.findById(commentNo);
        int likeCount = (int) comment.getCommentLikes().stream().filter(CommentLike::getLikeType).count();
        int dislikeCount = (int) comment.getCommentLikes().stream().filter(cl -> !cl.getLikeType()).count();

        Boolean userLike = commentLikeRepository.findById(id)
                .map(CommentLike::getLikeType)
                .orElse(null);

        CommentDto.CommentDtoS dto = new CommentDto.CommentDtoS(
                comment.getId(),
                comment.getParentNo(),
                comment.getCommentCont(),
                likeCount,
                dislikeCount
        );

        return new CommentDto.CommentDtoRequest(dto, userLike);
    }

}

