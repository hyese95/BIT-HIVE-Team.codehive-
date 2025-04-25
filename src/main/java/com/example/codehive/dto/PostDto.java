package com.example.codehive.dto;

import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import com.example.codehive.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Integer id;
    private String postCont;
    private String userProfileImg;
    @JsonFormat(pattern = "yyyy-MM-dd a hh시 mm분", shape = JsonFormat.Shape.STRING)
    private LocalDateTime postCreatedAt;
    private String imgUrl;
    private String category;
    private String userNickname;
    private Integer userNo;
    private int likeCount;
    private int dislikeCount;
    private int commentCount;

    @Getter
    @Setter
    public static class ModifyPostRequest {
        private int postNo;
        private String postCont;

    }
    public PostDto(Post post) {
            this.id = post.getId();
            this.postCont = post.getPostCont();
            this.userProfileImg=post.getUser().getProfileImgUrl();
            this.postCreatedAt = post.getPostCreatedAt();
            this.imgUrl = post.getImgUrl();
            this.category = post.getCategory();
            this.userNickname = post.getUser().getNickname();
            this.userNo = post.getUser().getId();
            this.commentCount = (post.getComment() != null) ? post.getComment().size() : 0;
            this.likeCount = 0;  // 기본값
            List<PostLike> postLikes=post.getPostLikes();// 기본값
        for(PostLike pl:postLikes){
            if(pl.getLikeType().equals(true)){
                this.likeCount++;
            }
        }
        this.dislikeCount = post.getPostLikes().size()-this.likeCount;
    }
    @Getter
    @Setter
    public static class PostSearchRequestDto {
        private String category;
        private int page;
        private int size;
    }

    @Getter
    @Setter
    public static class FindPostDto{
        private int postNo;
    }
}
