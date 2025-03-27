package com.example.codehive.service;

import com.example.codehive.entity.Comment;
import com.example.codehive.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final EntityManager entityManager;

    @Override
    public List<Comment> readComment(int id) {
        List<Comment> comment = commentRepository.findByPostNo(id);
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
        return commentRepository.findCommentContByPostNo(postNo);
    }

    @Override
    public List<Comment>  readCommentByPostNoAndParentNo(int postNo, Integer parentNo) {
        return commentRepository.findCommentContByPostNoAndParentNo(postNo,parentNo);
    }

    @Override
    @Transactional
    public List<Comment> removeCommentByPostNo(int postNo) {
        return commentRepository.deleteCommentByPostNo(postNo);
    }

    @Override
    public int getReplyCount(int parentNo) {
        return commentRepository.countByParentNo(parentNo);
    }

    @Override
    public void writeComments(Comment comment) {
        comment.setParentNo(comment.getPostNo());
        comment.setCommentCreatedAt(Instant.now());
        commentRepository.save(comment);
    }

    @Override
    public void modifyComment(Comment comment) {

    }

}
