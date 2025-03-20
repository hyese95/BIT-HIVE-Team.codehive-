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
import com.example.codehive.repository.PostRepository;
import org.springframework.data.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.Set;

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
        pageable = PageRequest.of(page, size);
        Page<Post> postPage=postService.readAllByCategory(pageable,"free");
        Page<PostDto> postDtoPage= postPage.map(PostDto::new);
        return postDtoPage;
    }
    @GetMapping("/free_post.do")
    public String freePost(Model model,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(defaultValue = "0") int page) {

        pageable = PageRequest.of(page, 10); // 기본 페이지 값 설정
        Page<Post> freePostPage = postService.readAllByCategory(pageable, "free");

        List<User> user = userService.findAll();
        Page<PostDto> postDto = freePostPage.map(PostDto::new);
        model.addAttribute("postDto", postDto);
        model.addAttribute("userList", user);
        return "community/free_post";
    }

    @GetMapping("/api/pnl_posts")
    @ResponseBody
    public Page<PostDto> loadMorePnlPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, Pageable pageable) {
        pageable = PageRequest.of(page, size);
        Page<Post> postPage = postService.readAllByCategory(pageable, "pnl");
        return postPage.map(PostDto::new);
    }
    @GetMapping("/pnl_post.do")
    public String pnlPost(Model model,
                          @PageableDefault(size = 10) Pageable pageable,
                          @RequestParam(defaultValue = "0") int page) {
        Page<Post> pnlPostPage = postService.readAllByCategory(pageable, "pnl");
        List<User> user = userService.findAll();
        User userName1=userService.findNicknameByUserNo(1);
        User userName2=userService.findNicknameByUserNo(2);
        User userName3=userService.findNicknameByUserNo(3);
        Page<PostDto> postDto = pnlPostPage.map(PostDto::new);
        model.addAttribute("userName1", userName1);
        model.addAttribute("userName2", userName2);
        model.addAttribute("userName3", userName3);
        model.addAttribute("postDto", postDto);
        model.addAttribute("userList", user);
        return "community/pnl_post";
    }
    @GetMapping("/api/chart_posts")
    @ResponseBody
    public Page<PostDto> loadMoreChartPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, Pageable pageable) {
        pageable = PageRequest.of(page, size);
        Page<Post> postPage=postService.readAllByCategory(pageable,"chart");
        return postPage.map(PostDto::new);
    }
    @GetMapping("/chart_post.do")
    public String chartPost(Model model,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(defaultValue = "0") int page) {
        pageable = PageRequest.of(page, 10); // 기본 페이지 값 설정
        Page<Post> chartPostPage = postService.readAllByCategory(pageable, "chart");
        List<User> user = userService.findAll();
        Page<PostDto> postDto = chartPostPage.map(PostDto::new);
        model.addAttribute("postDto", postDto);
        model.addAttribute("userList", user);
        return "community/chart_post";
    }
    @GetMapping("/api/expert_posts")
    @ResponseBody
    public Page<PostDto> loadMoreExpertPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, Pageable pageable) {
        pageable = PageRequest.of(page, size);
        Page<Post> postPage=postService.readAllByCategory(pageable,"expert");
        return postPage.map(PostDto::new);
    }
    @GetMapping("/expert_post.do")
    public String expertPost(Model model,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(defaultValue = "0") int page) {
        pageable = PageRequest.of(page, 10); // 기본 페이지 값 설정
        Page<Post> expertPostPage = postService.readAllByCategory(pageable, "expert");
        List<User> user = userService.findAll();
        Page<PostDto> postDto = expertPostPage.map(PostDto::new);
        model.addAttribute("postDto", postDto);
        model.addAttribute("userList", user);
        return "community/expert_post";
    }

    @GetMapping("/postDetail.do")
    public String detail(Model model,
                         @RequestParam int postNo
    ) {
        System.out.println("받은 아이디는" + postNo);
        Post post=postService.getPostByPostId(postNo);
        PostDto postDto=new PostDto(post);
        model.addAttribute("post", postDto);
        return "community/postDetail";
    }

    @GetMapping("search.do")
    public String search(Model model,
                         @RequestParam(defaultValue = "all") String category, @CookieValue(required = false) String recentKeywords
    ) {
        if (recentKeywords != null) {
            Set<String> keywordSet = new HashSet<>(Arrays.asList(recentKeywords.split("-")));
            model.addAttribute("keywordSet", keywordSet);
            model.addAttribute("category", category);

        }

        return "community/search";
    }

    @GetMapping("search_result.do")
    public String search_result(Model model,
                                @RequestParam(defaultValue = "all") String category,
                                @RequestParam String keyword,
                                @RequestParam(required = false) String sortType,
                                HttpServletRequest request,
                                HttpServletResponse response
    ) {
        //쿠키추가
        Cookie[] cookies = request.getCookies();
        Cookie keywordCookie = null;
        Set<String> keywordSet = new HashSet<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("recentKeywords")) {
                    keywordCookie = cookie;
                    String[] keywordArray = cookie.getValue().split("-");
                    keywordSet.addAll(Arrays.asList(keywordArray));
                    break;
                }
            }
        }
        keywordSet.add(keyword);
        if (keywordCookie == null) {
            keywordCookie = new Cookie("recentKeywords", keyword);
        } else {
            keywordCookie.setValue(String.join("-", keywordSet));
        }
        keywordCookie.setMaxAge(60 * 60 * 24 * 7);
        keywordCookie.setPath("/community");
        response.addCookie(keywordCookie);

        //검색로직
        Pageable pageable;
        if (category.equals("all")) { //통합검색
            pageable = PageRequest.of(0, 3);
            Page<Post> freePostPage = postService.readByCategoryWithKeyword("free", keyword, pageable);
            Page<Post> pnlPostPage = postService.readByCategoryWithKeyword("pnl", keyword, pageable);
            Page<Post> chartPostPage = postService.readByCategoryWithKeyword("chart", keyword, pageable);
            Page<Post> expertPostPage = postService.readByCategoryWithKeyword("expert", keyword, pageable);
            model.addAttribute("freePostPage", freePostPage);
            model.addAttribute("freePostCount", freePostPage.getTotalElements());
            model.addAttribute("pnlPostPage", pnlPostPage);
            model.addAttribute("pnlPostCount", pnlPostPage.getTotalElements());
            model.addAttribute("chartPostPage", chartPostPage);
            model.addAttribute("chartPostCount", chartPostPage.getTotalElements());
            model.addAttribute("expertPostPage", expertPostPage);
            model.addAttribute("expertPostCount", expertPostPage.getTotalElements());

        } else { //카테고리검색
            pageable = PageRequest.of(0, 2);
            Page<Post> postPage = postService.readByCategoryWithKeyword(category, keyword, pageable);
            model.addAttribute("postPage", postPage);
        }
        model.addAttribute("category", category);
        model.addAttribute("keyword", keyword);
        return "community/search_result";
    }

    @GetMapping("search/cookieDelete.do")
    public ResponseEntity<Void> cookieDelete(
            @RequestParam String keyword,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        try {
            System.out.println(keyword);
            Cookie[] cookies = request.getCookies();
            Cookie keywordCookie = null;
            Set<String> keywordSet = new HashSet<>();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("recentKeywords")) {
                    keywordCookie = cookie;
                    String[] keywordArray = cookie.getValue().split("-");
                    keywordSet.addAll(Arrays.asList(keywordArray));
                    keywordSet.remove(keyword);
                    break;
                }
            }
            if (!keywordSet.isEmpty()) {
                keywordCookie.setValue(String.join("-", keywordSet));
                keywordCookie.setMaxAge(60 * 60 * 24 * 7);
                keywordCookie.setPath("/community");
                response.addCookie(keywordCookie);
            } else {
                keywordCookie.setValue("");
                keywordCookie.setPath("/community");
                keywordCookie.setMaxAge(0);
                response.addCookie(keywordCookie);
            }
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

    }

    @GetMapping("/search/deleteAllKeywordCookie")
    public ResponseEntity<Void> deleteAllKeywordsCookie(HttpServletResponse response,
                                                        HttpServletRequest request
    ) {
        Cookie[] cookies = request.getCookies();
        Cookie keywordCookie = null;
        Set<String> keywordSet = new HashSet<>();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("recentKeywords")) {
                keywordCookie = cookie;
            }
        }
        keywordCookie.setValue("");
        keywordCookie.setPath("/community");
        keywordCookie.setMaxAge(0);
        response.addCookie(keywordCookie);
        System.out.println("전체삭제요");
        return ResponseEntity.status(200).build();
    }


    @GetMapping("api/search")
    @ResponseBody
    public Page<PostDto> loadMoreSearchResult(@RequestParam String category,
                                              @RequestParam String keyword,
                                              @RequestParam int page,
                                              @RequestParam(required = false, defaultValue = "null") String sortType
    ) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Post> postPage = postService.readByCategoryWithKeyword(category, keyword, sortType, pageable);
        System.out.println("category"+category);
        System.out.println("keyword"+keyword);
        System.out.println("page"+page);
        System.out.println("sortType"+sortType);
        System.out.println("###############"+postPage);
        return postPage.map(PostDto::new);
    }
}
