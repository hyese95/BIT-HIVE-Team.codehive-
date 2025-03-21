package com.example.codehive.controller;

import com.example.codehive.dto.UserDto;
import com.example.codehive.entity.User;
import com.example.codehive.service.PostService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;


@AllArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
    private UserService userService;

    @GetMapping("follow_list")
    public String followList(){
        return "/user/follow_list";
    }



}
