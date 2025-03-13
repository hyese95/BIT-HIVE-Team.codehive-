package com.example.codehive.controller;

import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import com.example.codehive.service.PostService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import com.example.codehive.entity.Post;
import com.example.codehive.repository.PostRepository;
import com.example.codehive.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
///community/postDetail.do
import java.util.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
@RequestMapping("/community")
@AllArgsConstructor
public class CommunityController {
    private final PostService postService;
    private final UserService userService;
    private final PostRepository postRepository;

    @GetMapping("/api/free_posts")
    @ResponseBody
    public Page<PostDto> loadMoreFreePosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, Pageable pageable) {
        Page<Post> postPage=postService.readAllByCategory(pageable,"free");
        return postPage.map(PostDto::new);
    }
    @GetMapping("/free_post.do")
    public String freePost(Model model,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(defaultValue = "0") int page) {

        pageable = PageRequest.of(page, 10); // 기본 페이지 값 설정
        Page<Post> freePostPage = postService.readAllByCategory(pageable, "free");

        List<User> users = userService.findAll();
        model.addAttribute("freePostPage", freePostPage);
        model.addAttribute("userList", users);
        return "community/free_post";
    }

    @GetMapping("/postDetail.do")
    public String detail(Model model,
                         @RequestParam (defaultValue = "1")int postNo
                         ) {
        System.out.println("받은 아이디는" + postNo);
        Post post=postService.getPostByPostId(postNo);
        model.addAttribute("post", post);
        return "community/postDetail";
    }

    @GetMapping("search.do")
    public String search(Model model,
                         @RequestParam(defaultValue = "all") String category
    ) {
        return "community/search";
    }

    @GetMapping("search_result.do")
    public String search_result(Model model,
                                @RequestParam(defaultValue = "all") String category,
                                @RequestParam String keyword,
                                HttpServletRequest request,
                                HttpServletResponse response
    ) {
        Pageable pageable;
        if (category.equals("all")) { //통합검색
            pageable = PageRequest.of(0, 2);
            Page<Post> freePostPage = postService.readByCategoryWithKeyword("free", keyword, pageable);
            Page<Post> pnlPostPage = postService.readByCategoryWithKeyword("pnl", keyword, pageable);
            Page<Post> chartPostPage = postService.readByCategoryWithKeyword("chart", keyword, pageable);
            Page<Post> expertPostPage = postService.readByCategoryWithKeyword("expert", keyword, pageable);
            model.addAttribute("freePostPage", freePostPage);
            model.addAttribute("pnlPostPage", pnlPostPage);
            model.addAttribute("chartPostPage", chartPostPage);
            model.addAttribute("expertPostPage", expertPostPage);
        } else { //카테고리검색
            pageable = PageRequest.of(0, 2);
            Page<Post> postPage = postService.readByCategoryWithKeyword(category, keyword, pageable);
            model.addAttribute("postPage", postPage);
        }
        model.addAttribute("category", category);
        model.addAttribute("keyword", keyword);



        return "community/search_result";
    }

    @GetMapping("api/search")
    @ResponseBody
    public Page<PostDto> loadMoreSearchResult(@RequestParam String category,
                                              @RequestParam String keyword,
                                              @RequestParam int page
    ) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Post> postPage = postService.readByCategoryWithKeyword(category, keyword, pageable);
        return postPage.map(PostDto::new);
    }


    @GetMapping("/pnl_post.do")
    public String pnlPost() {
        return "community/pnl_post";
    }

}