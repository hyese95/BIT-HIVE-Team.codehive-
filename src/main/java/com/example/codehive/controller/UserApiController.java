package com.example.codehive.controller;

import com.example.codehive.dto.UserDto;
import com.example.codehive.dto.UserUpdateDto;
import com.example.codehive.entity.Follow;
import com.example.codehive.entity.User;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {
    private UserService userService;

    @GetMapping("/me")
    public UserDto me() {
        Optional<User> userOpt = userService.readByUserNo(1);
        User user = userOpt.get();
        return UserDto.from(user);
    }

    @PatchMapping("/me")
    public ResponseEntity<Void> updateUserInfo(@RequestBody UserUpdateDto dto
    ) {
        try {
            if (dto.getNickname() != null) {
                userService.updateNickname(1, dto.getNickname());
            }
            if (dto.getSelfIntroduction() != null) {
                userService.updateSelfIntroduction(1, dto.getSelfIntroduction());
            }
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/me/summary")
    public UserDto meSummary() {
        Optional<User> userOpt = userService.readByUserNo(1);
        User user = userOpt.get();
        UserDto userDto=UserDto.from(user);
        userDto.setPostCount(userService.readPostsCount(1));
        userDto.setFollowingCount(userService.readFollowingCount(1));
        userDto.setFollowerCount(userService.readFollowersCount(1));
        return userDto;
    }

    @GetMapping("/me/followers")
    public List<Follow> meFollowers() {
        return userService.readFollowersByUserNo(1);
    }

    @GetMapping("/me/followings")
    public List<Follow> meFollowings() {
        return userService.readFollowingsByUserNo(1);
    }
}
