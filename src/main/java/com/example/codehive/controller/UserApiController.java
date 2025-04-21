package com.example.codehive.controller;

import com.example.codehive.dto.FollowDto;
import com.example.codehive.dto.PostDto;
import com.example.codehive.dto.UserDto;
import com.example.codehive.dto.UserUpdateDto;
import com.example.codehive.entity.Follow;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import com.example.codehive.service.PostService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {
    private final PostService postService;
    private UserService userService;

    @GetMapping("/me")
    public UserDto me(@AuthenticationPrincipal UserDetails loginUser) {
        String userId = loginUser.getUsername();
        User user = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보를 찾을 수 없습니다."));
        return UserDto.from(user);
    }


    @PatchMapping("/me")
    public ResponseEntity<Void> updateUserInfo(
            @AuthenticationPrincipal UserDetails loginUser,
            @RequestBody UserUpdateDto dto
    ) {
        try {
            String userId = loginUser.getUsername();
            User user = userService.readByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
            int userNo = user.getId();

            if (dto.getNickname() != null) {
                userService.updateNickname(userNo, dto.getNickname());
            }
            if (dto.getSelfIntroduction() != null) {
                userService.updateSelfIntroduction(userNo, dto.getSelfIntroduction());
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/me/summary")
    public UserDto meSummary(@AuthenticationPrincipal UserDetails loginUser) {
        String userId = loginUser.getUsername(); // 로그인한 사용자 ID 가져오기
        User user = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("로그인된 유저를 찾을 수 없습니다."));
        int userNo = user.getId();

        UserDto userDto = UserDto.from(user);
        userDto.setPostCount(userService.readPostsCount(userNo));
        userDto.setFollowingCount(userService.readFollowingCount(userNo));
        userDto.setFollowerCount(userService.readFollowersCount(userNo));
        return userDto;
    }

    @GetMapping("/me/followers")
    public List<FollowDto.Follower> meFollowers(
            @AuthenticationPrincipal UserDetails loginUser,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        String userId = loginUser.getUsername();
        int userNo = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음")).getId();
        Pageable pageable = PageRequest.of(page, size);
        return userService.readFollowersByUserNo(userNo, pageable);
    }

    @GetMapping("/me/followings")
    public List<FollowDto.Following> meFollowings(
            @AuthenticationPrincipal UserDetails loginUser,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        String userId = loginUser.getUsername();
        int userNo = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음")).getId();
        Pageable pageable = PageRequest.of(page, size);
        return userService.readFollowingsByUserNo(userNo, pageable);
    }

    @PostMapping("/follow/{followingUserNo}")
    public ResponseEntity<Void> follow(
            @AuthenticationPrincipal UserDetails loginUser,
            @PathVariable int followingUserNo
    ) {
        try {
            int userNo = userService.readByUserId(loginUser.getUsername())
                    .orElseThrow(() -> new RuntimeException("유저 없음")).getId();
            userService.follow(userNo, followingUserNo);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/unfollow/{followingUserNo}")
    public ResponseEntity<Void> unfollow(
            @AuthenticationPrincipal UserDetails loginUser,
            @PathVariable Integer followingUserNo
    ) {
        int userNo = userService.readByUserId(loginUser.getUsername())
                .orElseThrow(() -> new RuntimeException("유저 없음")).getId();
        userService.unfollow(userNo, followingUserNo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userNo}/followers")
    public List<FollowDto.Follower> getUserFollowers(
            @PathVariable Integer userNo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.readFollowersByUserNo(userNo, pageable);
    }

    @GetMapping("/{userNo}/followings")
    public List<FollowDto.Following> getUserFollowings(
            @PathVariable Integer userNo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.readFollowingsByUserNo(userNo, pageable);
    }

    @GetMapping("/{userNo}/isFollowing")
    public boolean isFollowing(
            @AuthenticationPrincipal UserDetails loginUser,
            @PathVariable Integer userNo
    ) {
        int myUserNo = userService.readByUserId(loginUser.getUsername())
                .orElseThrow(() -> new RuntimeException("유저 없음")).getId();
        return userService.isFollowing(myUserNo, userNo);
    }

    @GetMapping("/{userNo}/boards")
    public List<PostDto> getUserBoards(@PathVariable Integer userNo, @RequestParam (defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Post> posts= postService.readByUserNo(pageable, userNo);

        return posts.getContent().stream().map(PostDto::new).collect(Collectors.toList());

    }
}
