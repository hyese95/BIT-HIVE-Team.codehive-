package com.example.codehive.service;

import com.example.codehive.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> readComment(int id);
    Comment readChildComment(int parentNo);
    List<Comment> readCommentOfPost(int postNo);
    int getCommentCountByPostNo(int postNo);
    int getChildCommentCountByPostNoAndParentNo(int postNo,int parentNo);
    List<Comment>  readCommentByPostNo(int postNo);
    List<Comment>  readCommentByPostNoAndParentNo(int postNo,Integer parentNo);
    List<Comment> removeCommentByPostNo(int postNo);
    int getReplyCount(int parentNo);
    void modifyComment(Comment comment);
}
