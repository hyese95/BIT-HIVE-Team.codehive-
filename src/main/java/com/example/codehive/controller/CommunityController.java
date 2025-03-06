package com.example.codehive.controller;

import com.example.codehive.entity.Post;
import com.example.codehive.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/community")
@AllArgsConstructor
public class CommunityController {

    private final PostService postService;



    @GetMapping("free_post.do")
    public String freePost() {
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


        return "community/search_result";
    }

}
