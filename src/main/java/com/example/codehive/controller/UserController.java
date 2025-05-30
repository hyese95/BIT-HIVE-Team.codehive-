package com.example.codehive.controller;

import com.example.codehive.dto.UserDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import com.example.codehive.service.PostService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@AllArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {
    private final PostService postService;
    private UserService userService;

    @GetMapping("follow_list")
    public String followList(){
        return "/user/my_follow_list";
    }

    @GetMapping("{userNo}/follow_list")
    public String followList(@PathVariable int userNo, Model model){
        Optional<User> userOpt=userService.readByUserNo(userNo);
        User user=userOpt.get();
        UserDto userDto=UserDto.from(user);
        model.addAttribute("user",userDto);

        return "/user/user_follow_list";
    }

    @GetMapping("/{userNo}")
    public String userProfile(@PathVariable("userNo") Integer userNo, Model model){
        Optional<User> userOpt = userService.readByUserNo(userNo);
        User user = userOpt.get();
        UserDto userDto = UserDto.from(user);
        userDto.setPostCount(userService.readPostsCount(userNo));
        userDto.setFollowingCount(userService.readFollowingCount(userNo));
        userDto.setFollowerCount(userService.readFollowersCount(userNo));
        model.addAttribute("userDto", userDto);
        return "/user/profile";
    }

    @GetMapping ("/{userNo}/boards")
    public String userBoards(@PathVariable("userNo") Integer userNo, Model model){
        Optional<User> userOpt = userService.readByUserNo(userNo);
        User user = userOpt.get();
        UserDto userDto = UserDto.from(user);
        model.addAttribute("userDto", userDto);

        return "/user/user_boards";

    }



}
