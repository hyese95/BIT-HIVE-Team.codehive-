package com.example.codehive.service;

import com.example.codehive.entity.Comment;
import com.example.codehive.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment readComment(int id) {
        Comment comment = commentRepository.findComments(id);
        return comment;
    }

    @Override
    public Comment readChildComment(int commentNo) {
        return null;
    }

    @Override
    public List<Comment> readCommentOfPost(int postNo) {
        return List.of();
    }
}
