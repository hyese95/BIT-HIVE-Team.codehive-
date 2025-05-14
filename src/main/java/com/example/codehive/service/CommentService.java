package com.example.codehive.service;

import com.example.codehive.dto.CommentAndUserLikeDto;
import com.example.codehive.dto.CommentDto;
import com.example.codehive.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment readComment(int id);
    Comment readChildComment(int parentNo);
    List<Comment> readCommentOfPost(int postNo);
    int getCommentCountByPostNo(int postNo);
    int getChildCommentCountByPostNoAndParentNo(int postNo,int parentNo);
    List<Comment>  readCommentByPostNo(int postNo);
    List<Comment>  readCommentByPostNoAndParentNo(int postNo,Integer parentNo);
    void removeCommentByCommentNo(int commentNo);
    int getReplyCount(int parentNo);
    void writeComments(Comment comment);
    void modifyComment(Comment comment);
    void writeChildComments(Comment comment);
    public List<Comment> readAll();
    List<CommentDto> readCommentDtoByPostNo(int postNo);
    CommentDto modifyCommentDto(Comment comment);
    List<CommentAndUserLikeDto> getCommentsWithUserLikeType(int postNo, Integer userNo);
    public CommentAndUserLikeDto.responseToggle toggleCommentLikeStatus(int commentNo, int userNo, Boolean userLikeType);
}
