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
    Boolean likeType;
        public CommentLikeDto(CommentLike commentLike) {
            this.userNo=commentLike.getId().getUserNo();
            this.commentNo=commentLike.getId().getCommentNo();
            this.likeType=commentLike.getLikeType();
        }

    @Getter
    @Setter
    public static class CommentLikeRequest {
        private int userNo;
        private int commentNo;
        private Boolean likeType; // 1 = 좋아요, 0 = 싫어요, null = 취소
        // 생성자
        public CommentLikeRequest(CommentLikeDto commentLikedto) {
            this.userNo = commentLikedto.getUserNo();
            this.commentNo = commentLikedto.getCommentNo();
            this.likeType=commentLikedto.getLikeType();
        }}//게시글별로 상태를 조회하기 위한 조건 postNo 삽입

    @Getter
    @Setter
    public static class LikeRequest {
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
