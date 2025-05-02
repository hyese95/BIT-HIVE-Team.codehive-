package com.example.codehive.controller;

import com.example.codehive.dto.CommentLikeDto;
import com.example.codehive.service.CommentLikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class CommunityLikeAPIController {
    private CommentLikeService commentLikeService;

    @GetMapping("/comments/{commentNo}")
    public ResponseEntity<CommentLikeDto.LikeStatusResponse> getLikeTypeStatus(
            @PathVariable int commentNo,
            @RequestParam int userNo
            //           ,@AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
//        if(loginUser==null){
//            return ResponseEntity.badRequest().build();
//        }int loginUserNo=loginUser.getId();
    ) {
        userNo=1; // userNo=1로 하드코딩
        return ResponseEntity.ok(commentLikeService.getLikeStatus(commentNo,userNo));
    }

    @PostMapping("/comments/{commentNo}")
    public ResponseEntity<Void> pushLike(
            @PathVariable int commentNo,
            @RequestBody CommentLikeDto.LikeRequest request
            //           ,@AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
//        if(loginUser==null){
//            return ResponseEntity.badRequest().build();
//        }int loginUserNo=loginUser.getId();
    ) {
        commentLikeService.setLike(commentNo, request.getUserNo(), request.getLikeType());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comments/{commentNo}")
    public ResponseEntity<Void> removeLike(
            @PathVariable int commentNo,
            @RequestBody int userNo
            //           ,@AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
//        if(loginUser==null){
//            return ResponseEntity.badRequest().build();
//        }int loginUserNo=loginUser.getId();
    ) {
        commentLikeService.cancelLike(commentNo,userNo);
        return ResponseEntity.noContent().build();
    }
}
