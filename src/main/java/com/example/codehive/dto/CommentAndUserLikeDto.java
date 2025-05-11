package com.example.codehive.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentAndUserLikeDto {
    private Integer id;
    private Integer parentNo;
    private Integer postNo;
    private String commentCont;
    private String userProfileImg;
    @JsonFormat(pattern = "yyyy-MM-dd a hh시 MM분", shape = JsonFormat.Shape.STRING)
    private LocalDateTime CommentCreatedAt;
    private String category;
    private String userNickname;
    private Integer userNo;
    private int likeCount;
    private int dislikeCount;
    private Integer replyCount;
    private Boolean userLikeType;

    public CommentAndUserLikeDto(CommentDto commentDto,Boolean userLikeType) {
        this.id=commentDto.getId();
        this.parentNo=commentDto.getParentNo();
        this.postNo=commentDto.getPostNo();
        this.commentCont=commentDto.getCommentCont();
        this.userProfileImg=commentDto.getUserProfileImg();
        this.CommentCreatedAt=commentDto.getCommentCreatedAt();
        this.category=commentDto.getCategory();
        this.userNickname=commentDto.getUserNickname();
        this.userNo=commentDto.getUserNo();
        this.likeCount=commentDto.getLikeCount();
        this.dislikeCount=commentDto.getDislikeCount();
        this.replyCount=commentDto.getReplyCount();
        this.userLikeType=userLikeType;
    }
}
