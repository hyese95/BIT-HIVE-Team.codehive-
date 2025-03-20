package com.example.codehive.controller;

import com.example.codehive.dto.UserDto;
import com.example.codehive.entity.User;
import com.example.codehive.service.PostService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;


    @GetMapping("/me")
    public UserDto me() {
        Optional<User> userOpt = userService.readByUserNo(1);
        User user = userOpt.get();
        return UserDto.from(user);
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
}
