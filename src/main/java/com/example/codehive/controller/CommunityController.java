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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
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
        Page<Post> postPage = postService.readAllByCategory(pageable, "free");
        return postPage.map(PostDto::new);
    }

    @GetMapping("/free_post.do")
    public String freePost(Model model,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(defaultValue = "0") int page) {

        pageable = PageRequest.of(page, 10); // 기본 페이지 값 설정
        Page<Post> freePostPage = postService.readAllByCategory(pageable, "free");

        List<User> user = userService.findAll();
        model.addAttribute("freePostPage", freePostPage);
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
                          @RequestParam(defaultValue = "0") int page) {// 기본 페이지 값 설정
        pageable = PageRequest.of(page, 10);
        Page<Post> pnlPostPage = postService.readAllByCategory(pageable, "pnl");
        List<User> user = userService.findAll();
        model.addAttribute("pnlPostPage", pnlPostPage);
        model.addAttribute("userList", user);
        return "community/pnl_post";
    }

    @GetMapping("/postDetail.do")
    public String detail(Model model,
                         @RequestParam int postNo
    ) {
        System.out.println("받은 아이디는" + postNo);
        Post post = postService.getPostByPostId(postNo);
        model.addAttribute("post", post);
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
