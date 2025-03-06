package com.example.codehive.controller;

import com.example.codehive.entity.Post;
import com.example.codehive.repository.PostRepository;
import com.example.codehive.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/community")
@AllArgsConstructor
public class CommunityController {
    private final PostService postService;

    @GetMapping("/free_post.do")
    public String freePost(Post post, Model model) {
        Page<Post> postPage=postService.ReadAllByCategory( PageRequest.of(0, 10), "free");
        model.addAttribute("postPage",postPage);
        return "community/free_post";
    }

    @GetMapping("/pnl_post.do")
    public String pnlPost() {
        return "community/pnl_post";
    }

}