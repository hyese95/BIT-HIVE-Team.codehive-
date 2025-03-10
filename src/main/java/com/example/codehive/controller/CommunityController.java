package com.example.codehive.controller;

import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import com.example.codehive.service.PostService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    private final UserService userService;

    @GetMapping("/free_post.do")
    public String freePost(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Post> postPage = postService.ReadAllByCategory(PageRequest.of(page, 10), "free");
        List<User> users = userService.findAll();
        model.addAttribute("postPage", postPage);
        model.addAttribute("userList", users);
        return "community/free_post";
    }

    @GetMapping("search.do")
    public String search(@RequestParam (required = false,defaultValue = "all") String category,
                         Model model) {

        model.addAttribute("category", category);
        return "community/search";
    }

    @PostMapping("search_result.do")
    public String searchResult(Model model,
                               @RequestParam (required = false,defaultValue = "all") String category,
                               @RequestParam (required = false) String keyword) {

        List<Post> searchResult=postService.findByCategoryWithKeyword(category,keyword);

        model.addAttribute("searchResult", searchResult);
        model.addAttribute("postService", postService);


        return "community/search_result";
    }



    @GetMapping("/pnl_post.do")
    public String pnlPost() {
        return "community/pnl_post";
    }

}
