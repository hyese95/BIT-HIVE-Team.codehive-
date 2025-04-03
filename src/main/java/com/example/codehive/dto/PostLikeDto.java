package com.example.codehive.dto;

import com.example.codehive.entity.PostLike;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeDto {
    private Integer postNo;// 댓글 번호
    private Integer userNo;
    private Integer likeCount;       // 좋아요 갯수
    private Integer dislikeCount;
    private boolean likeType;


    public PostLikeDto(PostLike postLike) {
        this.postNo = postLike.getPostNo();
        this.userNo = postLike.getUserNo();
        this.likeCount = 0;
        List<PostLike> postLikes=postLike.getPost().getPostLikes();
        for(PostLike pl:postLikes){
            if(pl.getLikeType().equals(true)){
                this.likeCount++;
            }
            else if(pl.getLikeType().equals(false)){
                this.dislikeCount++;
            }
        }
        this.dislikeCount = postLike.getPost().getPostLikes().size()-this.likeCount;
        this.likeType=postLike.getLikeType();
    }
}
