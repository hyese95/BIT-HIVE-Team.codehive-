package com.example.codehive.controller;

import com.example.codehive.dto.CommentLikeDto;
import com.example.codehive.entity.Comment;
import com.example.codehive.entity.CommentLike;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import com.example.codehive.service.CommentLikeService;
import com.example.codehive.service.CommentService;
import com.example.codehive.service.PostLikeService;
import com.example.codehive.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/community/LikeStatus")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class CommunityLikeAPIController {
    private CommentLikeService commentLikeService;
    private CommentService commentService;
    private PostLikeService postLikeService;
    private PostService postService;
    @GetMapping("/comments/{commentNo}")
    public ResponseEntity<CommentLikeDto.CommentLikeRequest> getLikeTypeStatus(
            @PathVariable int commentNo
            ,@RequestParam int userNo
            //           ,@AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
//        if(loginUser==null){
//            return ResponseEntity.badRequest().build();
//        }int loginUserNo=loginUser.getId();
    ) {
        CommentLike commentLike = commentLikeService.GetCommentLike(userNo, commentNo);
        if (commentLike == null) {
            return ResponseEntity.ok().build();
        }
        CommentLikeDto commentLikeDto = new CommentLikeDto(commentLike);
        CommentLikeDto.CommentLikeRequest request =
                new CommentLikeDto.CommentLikeRequest(commentLikeDto);
        return ResponseEntity.ok(request);
    }
    @PostMapping("/comments/{commentNo}")
    public ResponseEntity<?> toggleCommentLike(
            @PathVariable int commentNo,
            @RequestBody CommentLikeDto.LikeRequest request
            //           ,@AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
//        if(loginUser==null){
//            return ResponseEntity.badRequest().build();
//        }int loginUserNo=loginUser.getId();
    ) {
        int loginUserNo=1; //임시 하드코딩
        // 1. likeType이 null 이면 삭제 (토글시 삭제)
        if(request.getLikeType()==null){
            return commentLikeService.deleteCommentLike(loginUserNo, commentNo);
        }
        // 2. likeType이 null 이 아니면 새로 설정
        commentLikeService.setCommentLike(loginUserNo, commentNo, request.getLikeType());
        return ResponseEntity.ok(Map.of(
                "commentNo", commentNo,
                "loginUserNo", loginUserNo,
                "likeType", request.getLikeType()
        ));
    }
    @GetMapping("/posts/{postNo}")
    public ResponseEntity<?> getPostLikeTypeStatus(
             @PathVariable int postNo
            ,@RequestParam int userNo
            //           ,@AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
//        if(loginUser==null){
//            return ResponseEntity.badRequest().build();
//        }int loginUserNo=loginUser.getId();
    ) {
        userNo=1;//하드코딩
        PostLike postLike=postLikeService.GetPostLike(userNo, postNo);
//        PostLike postLike=postLikeService.GetPostLike(loginUserNo, postNo);
        if (postLike == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(postLike);
    }
    @PostMapping("/posts/{postNo}")
    public ResponseEntity<?> togglePostLike(
            @PathVariable int postNo,
            @RequestBody PostLike postLike
            //           ,@AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
//        if(loginUser==null){
//            return ResponseEntity.badRequest().build();
//        }int loginUserNo=loginUser.getId();
    ) {
        int userNo=1;//임시 하드코딩
        // request.getLikeType() 이 null 이면 삭제 코드 추가
        // Body에 담긴 postLike가 같아도 삭제 -> 완벽한 토글
        PostLike existingPostLike=postLikeService.GetPostLike(userNo, postNo);
        if(postLike.getLikeType()==null){
           return postLikeService.DeletePostLike(userNo, postNo);
//            postLikeService.DeletePostLike(loginUserNo, postNo);
        }
        // 그후에 다시 생성, 서비스 코드에 필요한거 다 해뒀다고 생각함
        postLikeService.CreatePostLike(userNo, postNo, postLike.getLikeType());
//        postLikeService.CreatePostLike(loginUserNo, postNo, postLike.getLikeType());
        return ResponseEntity.ok(postLike);
    }
}
