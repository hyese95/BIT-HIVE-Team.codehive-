package com.example.codehive.dto;

import com.example.codehive.entity.CommentLike;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeDto {
    int userNo;
    int commentNo;
    boolean likeType;
        public CommentLikeDto(CommentLike commentLike) {
            this.userNo=commentLike.getId().getUserNo();
            this.commentNo=commentLike.getId().getCommentNo();
            this.likeType=commentLike.getLikeType();
        }

    @Getter
    @Setter
    public static class LikeRequest {
        private Integer userNo;
        private Boolean likeType; // true: 좋아요, false: 싫어요
    }

    @Getter
    @Setter
    public static class LikeStatusResponse {
        private boolean likeType; // true, false, null

        public LikeStatusResponse(Boolean likeType) {
            this.likeType = likeType;
        }

        public Boolean getLikeType() {
            return likeType;
        }
    }
}
