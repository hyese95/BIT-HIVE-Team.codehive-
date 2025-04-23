package com.example.codehive.dto;

import com.example.codehive.entity.Comment;
import com.example.codehive.entity.CommentLike;
import com.example.codehive.entity.PostLike;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    @NotNull
    int id;
    @NotNull
    int postNo;
    @NotNull
    int userNo;
    @NotNull
    String commentCont;
    LocalDateTime commentCreatedAt;
    int parentNo;
    int commentLikeCount;
    int commentDislikeCount;
    int replyCount;
    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.postNo = comment.getPost().getId();
        this.userNo = comment.getUserNo().getId();
        this.commentCont = comment.getCommentCont();
        this.commentCreatedAt = LocalDateTime.now();
        this.commentLikeCount=0;
        List<CommentLike> CommentLikes=comment.getCommentLikes();
        for(CommentLike cl:CommentLikes){
            if(cl.getLikeType().equals(true)){
                this.commentLikeCount++;
            }
        }
        this.commentDislikeCount = comment.getCommentLikes().size()-this.commentLikeCount;
        this.replyCount=comment.getChildComments().size();
    }
}
