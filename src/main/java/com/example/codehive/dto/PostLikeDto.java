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
    private Integer likeCount;       // 좋아요 갯수
    private Integer dislikeCount;
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

    public static class PostLikeDtoStaus{

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class RequestToggle{
            private Boolean userLikeType; //로그인한 유저의 좋아요 값을 받아오는 request
        }

        @Getter
        @Setter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ResponseToggle{
            private Integer postNo;
            private Boolean userLikeType;
            private int likeCount;
            private int dislikeCount;
        }
    }
}
