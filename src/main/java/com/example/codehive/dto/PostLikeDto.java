package com.example.codehive.dto;

import com.example.codehive.entity.PostLike;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeDto {

    @JsonProperty("postNo")
    private Integer postNo;// 댓글 번호

    private int likeCount;       // 좋아요 갯수
    private int dislikeCount;
    private Integer userNo;
    private boolean likeType;

    public PostLikeDto(Integer postNo, Integer likeCount, Integer dislikeCount, Integer userNo, Boolean likeType) {
        this.postNo = postNo;
        this.likeCount = likeCount != null ? likeCount : 0;
        this.dislikeCount = dislikeCount != null ? dislikeCount : 0;
        this.userNo = userNo;
        this.likeType = likeType != null && likeType;
    }

    public PostLikeDto(PostLike postLike) {
        this.postNo = postLike.getPostNo();
        this.userNo = postLike.getUserNo();
        this.likeCount = 0;
        List<PostLike> postLikes=postLike.getPost().getPostLikes();
        for(PostLike pl:postLikes){
            if(pl.getLikeType().equals(true)){
                this.likeCount++;
            }
        }
        this.dislikeCount = postLike.getPost().getPostLikes().size()-this.likeCount;
        this.likeType=postLike.getLikeType();
    }
}
