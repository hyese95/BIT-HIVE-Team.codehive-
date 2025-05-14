package com.example.codehive.controller;

import com.example.codehive.dto.CommentAndUserLikeDto;
import com.example.codehive.dto.CommentDto;
import com.example.codehive.dto.CommentLikeDto;
import com.example.codehive.entity.PostLike;
import com.example.codehive.entity.User;
import com.example.codehive.jwt.JwtUtil;
import com.example.codehive.security.CustomUserDetails;
import com.example.codehive.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community/LikeStatus")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class CommunityLikeAPIController {
    private CommentLikeService commentLikeService;
    private CommentService commentService;
    private PostLikeService postLikeService;
    private UserService userService;
    private JwtUtil jwtUtil;

    @PostMapping("/comments/{commentNo}")
    public ResponseEntity<?> toggleCommentLike(
            @PathVariable int commentNo
            ,@AuthenticationPrincipal CustomUserDetails userDetails
            ,@RequestBody CommentAndUserLikeDto.requestToggle requestToggle) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if (loginUser==null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        int loginUserNo=loginUser.getId();
        CommentAndUserLikeDto.responseToggle result = commentService.toggleCommentLikeStatus(
                commentNo,
                loginUserNo,
                requestToggle.getUserLikeType()
        );
        return ResponseEntity.ok(result);
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
