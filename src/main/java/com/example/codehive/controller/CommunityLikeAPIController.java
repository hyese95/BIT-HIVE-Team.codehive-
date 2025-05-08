package com.example.codehive.controller;

import com.example.codehive.dto.CommentDto;
import com.example.codehive.dto.CommentLikeDto;
import com.example.codehive.entity.PostLike;
import com.example.codehive.entity.User;
import com.example.codehive.security.CustomUserDetails;
import com.example.codehive.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/community/LikeStatus")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class CommunityLikeAPIController {
    private CommentLikeService commentLikeService;
    private CommentService commentService;
    private PostLikeService postLikeService;
    private UserService userService;

    @GetMapping("/posts/{postNo}/comments")
    public ResponseEntity<List<CommentDto.CommentDtoRequest>> getCommentLikeTypeStatus(
            @PathVariable int postNo
           ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }int loginUserNo=loginUser.getId();
        List<CommentDto.CommentDtoRequest> request=commentService.getCommentsWithLikes(postNo,loginUserNo);
        return ResponseEntity.ok(request);
    }
    @PostMapping("/comments/{commentNo}")
    public ResponseEntity<CommentDto.CommentDtoRequest> toggleCommentLike(
            @PathVariable int commentNo
            ,@AuthenticationPrincipal CustomUserDetails userDetails
            ,@RequestBody CommentLikeDto commentLikeDto) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }if(commentLikeDto.getLikeType()!=null){
            deleteCommentLike(commentNo, loginUser.getId());
        }if(commentLikeDto.getLikeType()!=null){
            deleteCommentLike(commentNo, loginUser.getId());
        }
            CommentDto.CommentDtoRequest updatedComment =
                    commentService.toggleCommentLike(commentNo, commentLikeDto.getUserNo(), commentLikeDto.getLikeType());
            return ResponseEntity.ok(updatedComment);
        }

    @DeleteMapping("/{commentNo}")
    public ResponseEntity<Void> deleteCommentLike(
            @PathVariable int commentNo,
            @RequestParam int userNo) {
        commentLikeService.deleteCommentLike(commentNo, userNo,null);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/{postNo}")
    public ResponseEntity<?> getPostLikeTypeStatus(
             @PathVariable int postNo
            ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }int loginUserNo=loginUser.getId();
        PostLike postLike=postLikeService.GetPostLike(loginUserNo, postNo);
        if (postLike == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(postLike);
    }
    @PostMapping("/posts/{postNo}")
    public ResponseEntity<?> togglePostLike(
            @PathVariable int postNo,
            @RequestBody PostLike postLike
           ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }int loginUserNo=loginUser.getId();
        // request.getLikeType() 이 null 이면 삭제 코드 추가
        // Body에 담긴 postLike가 같아도 삭제 -> 완벽한 토글
        if(postLike.getLikeType()==null){
           return postLikeService.DeletePostLike(loginUserNo, postNo, null);
        }
        // 그후에 다시 생성, 서비스 코드에 필요한거 다 해뒀다고 생각함
        postLikeService.CreatePostLike(loginUserNo, postNo, postLike.getLikeType());
        return ResponseEntity.ok(postLike);
    }
}
