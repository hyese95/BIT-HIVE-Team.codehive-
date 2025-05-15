package com.example.codehive.controller;

import com.example.codehive.dto.CommentAndUserLikeDto;
import com.example.codehive.dto.PostLikeDto;
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
            ,HttpServletRequest request
    ) {
        String token= jwtUtil.resolveToken(request);
        if(token==null){
            PostLikeDto.PostLikeDtoStaus.ResponseToggle postLike=postLikeService.getPostLike(null, postNo);
            return ResponseEntity.ok(postLike);
        }
        String userId= jwtUtil.getUsername(token);
        User loginUser=userService.readByUserId(userId).orElseThrow();
        Integer loginUserNo=loginUser.getId();
        PostLikeDto.PostLikeDtoStaus.ResponseToggle postLike=postLikeService.getPostLike(loginUserNo, postNo);
        return ResponseEntity.ok(postLike);
    }
    @PostMapping("/posts/{postNo}")
    public ResponseEntity<?> togglePostLike(
            @PathVariable int postNo,
            @RequestBody PostLikeDto.PostLikeDtoStaus.RequestToggle request
           ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }int loginUserNo=loginUser.getId();
        // 그후에 다시 생성, 서비스 코드에 필요한거 다 해뒀다고 생각함
        PostLikeDto.PostLikeDtoStaus.ResponseToggle response =
                postLikeService.togglePostLike(loginUserNo, postNo, request.getUserLikeType());
        return ResponseEntity.ok(response);
    }
}
