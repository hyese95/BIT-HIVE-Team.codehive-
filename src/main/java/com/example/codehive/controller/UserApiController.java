package com.example.codehive.controller;

import com.example.codehive.dto.UserUpdateDto;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {
    private UserService userService;

    @PatchMapping("/me")
    public ResponseEntity<Void> updateUserInfo(@RequestBody UserUpdateDto dto
    ) {
        try {
            if (dto.getNickname() != null) {
                userService.updateNickname(1, dto.getNickname());
            }
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
