package com.example.codehive.controller;

import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import com.example.codehive.service.PostService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/community")
@AllArgsConstructor
public class CommunityController {
    private final PostService postService;
    private final UserService userService;

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

    @GetMapping("postDetail.do")
    public String detail(Model model
                         ) {
        System.out.println("받은 아이디는" + 6);
        Post post=postService.getPostByPostId(6);
        model.addAttribute("post", post);
        return "community/postDetail";
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
