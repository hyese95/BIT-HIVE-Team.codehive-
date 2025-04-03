package com.example.codehive.dto;

import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
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

    @Getter
    @Setter
    public static class ModifyPostRequest {
        private int postNo;
        private String postCont;

    }
    public PostDto(Post post) {
            this.id = post.getId();
            this.postCont = post.getPostCont();
            this.postCreatedAt = post.getPostCreatedAt();
            this.imgUrl = post.getImgUrl();
            this.category = post.getCategory();
            this.userNickname = post.getUser().getNickname();
            this.userId = post.getUser().getId();
            this.commentCount = (post.getComment() != null) ? post.getComment().size() : 0;
            this.likeCount = 0;  // 기본값
            this.dislikeCount = 0; // 기본값
    }
    public PostDto(Integer postNo, int likeCount, int dislikeCount) {
        this.id = postNo;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }
}
