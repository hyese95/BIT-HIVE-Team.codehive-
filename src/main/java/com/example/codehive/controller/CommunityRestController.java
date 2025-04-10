package com.example.codehive.controller;

import com.example.codehive.entity.Post;
import com.example.codehive.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class CommunityRestController {
    private final PostService postService;
    @GetMapping("read/{category}")
    public ResponseEntity<Page<Post>> read(@PathVariable String category) {
        return null;
    }

}
