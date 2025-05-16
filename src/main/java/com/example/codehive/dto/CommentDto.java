package com.example.codehive.dto;

import com.example.codehive.entity.Comment;
import com.example.codehive.entity.CommentLike;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
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

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.parentNo=comment.getParentNo();
        this.postNo=comment.getPostNo();
        this.commentCont = comment.getCommentCont();
        this.CommentCreatedAt = comment.getCommentCreatedAt();
        this.userNo=comment.getUserNo().getId();
        this.userNickname=comment.getUserNo().getNickname();
        this.userProfileImg=comment.getUserNo().getProfileImgUrl();
        this.category=comment.getPost().getCategory();
        List<CommentLike> commentLikes = comment.getCommentLikes();
        for(CommentLike cl:commentLikes){
            if(cl.getLikeType().equals(true)){
                this.likeCount++;
            }
        }
        this.dislikeCount = comment.getCommentLikes().size()-this.likeCount;
        if(comment.getChildComments()==null){
            this.replyCount = 0;
        }else{this.replyCount = comment.getChildComments().size();}
    }

    @Getter @Setter
    public static class CommentDtoS {
        private int id;
        private Integer parentNo;
        private String commentCont;
        private int likeCount;
        private int dislikeCount;

        public CommentDtoS(int id, Integer parentNo, String commentCont, int likeCount, int dislikeCount) {
            this.id = id;
            this.parentNo = parentNo;
            this.commentCont = commentCont;
            this.likeCount = likeCount;
            this.dislikeCount = dislikeCount;
        }
    }
    @Getter
    @Setter
    public static class ModifyRequest {
        private Integer id;
        private String commentCont;

        public ModifyRequest(Integer id,String commentCont) {
            this.id = id;
            this.commentCont = commentCont;

        }
    }
}
