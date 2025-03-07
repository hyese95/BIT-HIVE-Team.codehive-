package com.example.codehive.controller;

import com.example.codehive.entity.Post;
import com.example.codehive.service.PostService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import com.example.codehive.entity.Post;
import com.example.codehive.repository.PostRepository;
import com.example.codehive.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    @GetMapping("/free_post.do")
    public String freePost(Post post, Model model) {
        Page<Post> postPage = postService.ReadAllByCategory(PageRequest.of(0, 10), "free");
        model.addAttribute("postPage", postPage);
        return "community/free_post";
    }

    @GetMapping("search.do")
    public String search(@RequestParam(required = false, defaultValue = "all") String category,
                         Model model,
                         HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        List<String> keywordCookies = new ArrayList<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().contains("keyword")) {
                    keywordCookies.add(cookie.getValue());
                }
            }
        }

        model.addAttribute("keywordCookies", keywordCookies);
        model.addAttribute("category", category);
        return "community/search";
    }

    @PostMapping("search_result.do")
    public String searchResult(Model model,
                               @RequestParam(required = false, defaultValue = "all") String category,
                               @RequestParam(required = false) String keyword,
                               HttpServletRequest req,
                               HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        int cookiesLength = 0;

        boolean isExist = false;
        if (cookies != null) {
            cookiesLength = cookies.length;
            for (Cookie cookie : cookies) {
                if (cookie.getName().contains("keyword") && cookie.getValue().equals(keyword)) {
                    isExist = true;
                    break;
                }
            }
        }
        if (!isExist) {
            int i = cookiesLength + 1;
            Cookie cookie = new Cookie("keyword" + i, keyword);
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/community");
            resp.addCookie(cookie);
        }


        List<Post> searchResult = postService.findByCategoryWithKeyword(category, keyword);
        String categoryKrName = switch (category) {
            case "free" -> "자유게시판";
            case "pnl" -> "손익인증게시판";
            case "expert" -> "전문가게시판";
            case "chart" -> "차트분석게시판";
            default -> "통합검색";
        };

        model.addAttribute("categoryKrName", categoryKrName);
        model.addAttribute("category", category);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("postService", postService);


        return "community/search_result";
    }

    @GetMapping("/search/cookie.do")
    public String searchCookieController(Model model,
                                         HttpServletResponse resp,
                                         HttpServletRequest req,
                                         @RequestParam(required = false, defaultValue = "all") String category,
                                         @RequestParam(required = false) String keyword,
                                         @RequestParam(required = false) boolean deleteAll) {
        Cookie[] cookies = req.getCookies();
        Cookie cookie;
        for (Cookie c : cookies) {
            if (c.getName().contains("keyword") && c.getValue().equals(keyword)) {
                cookie=c;
                c.setPath("/community");
                c.setMaxAge(0);
                resp.addCookie(c);
                break;
            }
        }

        if (deleteAll) {
            for (Cookie c : cookies) {
                if (c.getName().contains("keyword")) {
                    c.setPath("/community");
                    c.setMaxAge(0);
                    resp.addCookie(c);
                }
            }
        }

        return "redirect:/community/search.do?category=" + category;
    }


    @GetMapping("/pnl_post.do")
    public String pnlPost() {
        return "community/pnl_post";
    }

}