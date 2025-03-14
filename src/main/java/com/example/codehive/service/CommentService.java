package com.example.codehive.service;

import com.example.codehive.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment readComment(int id);
    Comment readChildComment(int commentNo);
    List<Comment> readCommentOfPost(int postNo);
}
