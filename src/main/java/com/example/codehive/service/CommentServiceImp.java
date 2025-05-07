package com.example.codehive.service;

import com.example.codehive.dto.CommentDto;
import com.example.codehive.entity.Comment;
import com.example.codehive.entity.Post;
import com.example.codehive.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;
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
    public List<CommentDto.CommentDtoRequest> getCommentsWithLikes(int postNo, int userNo) {
        return commentRepository.findCommentsWithUserLike(postNo, userNo);
    }
}
