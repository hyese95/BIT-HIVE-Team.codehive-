package com.example.codehive.controller;

import com.example.codehive.dto.UserDto;
import com.example.codehive.entity.User;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        UserDto userDTO = UserDto.builder()
                .id(user.getId())
                .profileImgUrl(user.getProfileImgUrl())
                .nickname(user.getNickname())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .selfIntroduction(user.getSelfIntroduction())
                .build();


        return userDTO;
    }
}
