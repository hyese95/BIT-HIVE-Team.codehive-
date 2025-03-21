package com.example.codehive.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentLikeCountDTO {
    private Integer commentNo;    // 댓글 번호
    private Integer likeCount;       // 좋아요 갯수
    private Integer dislikeCount;    // 싫어요 갯수

    public CommentLikeCountDTO(Integer commentNo, Integer likeCount, Integer dislikeCount) {
        this.commentNo = commentNo;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }
}
