package com.example.codehive.dto;

import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class PostDto {
    private Integer id;
    private String postCont;
    private Instant postCreatedAt;
    private String imgUrl;
    private String category;
    private String userNickname;
    private Integer userId;
    private int likeCount;
    private int dislikeCount;
    private int commentCount;

    public PostDto(Post post) {
            this.id = post.getId();
            this.postCont = post.getPostCont();
            this.postCreatedAt = post.getPostCreatedAt();
            this.imgUrl = post.getImgUrl();
            this.category = post.getCategory();
            this.userNickname = post.getUser().getNickname();
            this.userId = post.getUser().getId();
            this.commentCount=post.getComment().size();

            // 좋아요/싫어요 카운트
            this.likeCount = 0;

            List<PostLike> postLikes=post.getPostLikes();
            for(PostLike pl:postLikes){
                if(pl.getLikeType().equals(true)){
                    this.likeCount++;
                }
            }
            this.dislikeCount = post.getPostLikes().size()-this.likeCount;


    }
}
