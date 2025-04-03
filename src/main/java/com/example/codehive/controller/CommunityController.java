package com.example.codehive.controller;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.dto.PostDto;
import com.example.codehive.dto.PostLikeDto;
import com.example.codehive.entity.*;
import com.example.codehive.repository.*;
import com.example.codehive.service.*;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.*;

@Controller
@RequestMapping("/community")
@AllArgsConstructor
public class CommunityController {
    private final EntityManager entityManager;
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;
    private final PostLikeService postLikeService;
    private final CommentRepository commentRepository;
    private final Logger logger = LoggerFactory.getLogger(CommunityController.class);
    private final PostLikeRepository postLikeRepository;

    public String convertNewlineToBr(String text) {
        return text.replace("\n", "<br>");
    }
    @PostMapping("/api/postLike/toggle")
    @ResponseBody
    public ResponseEntity<PostLikeDto> toggleLike(@RequestBody PostLikeDto postLikeDto)
    {
        logger.info("Received postLikeDto: {}", postLikeDto);
        int userNo=postLikeDto.getUserNo();
        if(userNo==0){
            userNo=1;
        }
        PostLikeDto result = postLikeService.toggleLike(userNo,postLikeDto.getPostNo(),postLikeDto.isLikeType());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/free_posts")
    @ResponseBody
    public Page<PostDto> loadMoreFreePosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, Pageable pageable) {
        pageable = PageRequest.of(page, size);
        Page<Post> postPage=postService.readAllByCategory(pageable,"free");
        postPage.forEach(post -> post.setPostCont(convertNewlineToBr(post.getPostCont())));
        Page<PostDto> postDtoPage= postPage.map(PostDto::new);
        return postDtoPage;
    }
    @PostMapping("/api/free_post_write")
    @ResponseBody
    public ResponseEntity<PostDto> writeFreePost(@RequestBody PostDto postDto) {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            throw new IllegalArgumentException("사용자가 없습니다.");
        }
        User randomUser = users.get(new Random().nextInt(users.size()));
        postDto.setUserId(randomUser.getId());
        postDto.setCategory("free");
        postDto.setUserNickname(randomUser.getNickname());
        postDto.setPostCreatedAt(Instant.now());
        PostDto savedPost = postService.createPost(postDto);
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/free_post.do")
    public String freePost(Model model,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(defaultValue = "0") int page) {
        pageable = PageRequest.of(page, 10); // 기본 페이지 값 설정
        Page<Post> freePostPage = postService.readAllByCategory(pageable, "free");
        User user = userService.readByUserNo(1).orElse(null);
        model.addAttribute("user", user);
        Page<PostDto> postDto = freePostPage.map(PostDto::new);
        model.addAttribute("postDto", postDto);
        List<Comment> comments = commentService.readAll();
        List<PostLikeDto> postLikeDto =postLikeRepository.getPostLikeAndDislike();
        model.addAttribute("comments", comments);
        Map<Integer, CommentLikeCountDTO> cntCommentLike = commentLikeService.countCommentLikes();
        model.addAttribute("cntCommentLike", cntCommentLike);
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
    @PostMapping("/api/pnl_post_write")
    @ResponseBody
    public ResponseEntity<PostDto> writePnlPost(@RequestBody PostDto postDto) {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            throw new IllegalArgumentException("사용자가 없습니다.");
        }
        User randomUser = users.get(new Random().nextInt(users.size()));
        postDto.setUserId(randomUser.getId());
        postDto.setCategory("pnl");
        postDto.setUserNickname(randomUser.getNickname());
        postDto.setPostCreatedAt(Instant.now());
        PostDto savedPost = postService.createPost(postDto);
        return ResponseEntity.ok(savedPost);
    }
    @GetMapping("/pnl_post.do")
    public String pnlPost(Model model,
                          @PageableDefault(size = 10) Pageable pageable,
                          @RequestParam(defaultValue = "0") int page) {
        Page<Post> pnlPostPage = postService.readAllByCategory(pageable, "pnl");
        User userName1=userService.findNicknameByUserNo(1);
        User userName2=userService.findNicknameByUserNo(2);
        User userName3=userService.findNicknameByUserNo(3);
        User user = userService.readByUserNo(1).orElse(null);
        model.addAttribute("user", user);
        Page<PostDto> postDto = pnlPostPage.map(PostDto::new);
        model.addAttribute("userName1", userName1);
        model.addAttribute("userName2", userName2);
        model.addAttribute("userName3", userName3);
        model.addAttribute("postDto", postDto);
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
    @PostMapping("/api/chart_post_write")
    @ResponseBody
    public ResponseEntity<PostDto> writeChartPost(@RequestBody PostDto postDto) {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            throw new IllegalArgumentException("사용자가 없습니다.");
        }
        User randomUser = users.get(new Random().nextInt(users.size()));
        postDto.setUserId(randomUser.getId());
        postDto.setCategory("chart");
        postDto.setUserNickname(randomUser.getNickname());
        postDto.setPostCreatedAt(Instant.now());
        PostDto savedPost = postService.createPost(postDto);
        return ResponseEntity.ok(savedPost);
    }
    @GetMapping("/chart_post.do")
    public String chartPost(Model model,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(defaultValue = "0") int page) {
        pageable = PageRequest.of(page, 10); // 기본 페이지 값 설정
        Page<Post> chartPostPage = postService.readAllByCategory(pageable, "chart");
        User user = userService.readByUserNo(1).orElse(null);
        model.addAttribute("user", user);
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
    @PostMapping("/api/expert_post_write")
    @ResponseBody
    public ResponseEntity<PostDto> writeExpertPost(@RequestBody PostDto postDto) {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            throw new IllegalArgumentException("사용자가 없습니다.");
        }
        User randomUser = users.get(new Random().nextInt(users.size()));
        postDto.setUserId(randomUser.getId());
        postDto.setCategory("expert");
        postDto.setUserNickname(randomUser.getNickname());
        postDto.setPostCreatedAt(Instant.now());
        PostDto savedPost = postService.createPost(postDto);
        return ResponseEntity.ok(savedPost);
    }
    @GetMapping("/expert_post.do")
    public String expertPost(Model model,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(defaultValue = "0") int page) {
        pageable = PageRequest.of(page, 10); // 기본 페이지 값 설정
        Page<Post> expertPostPage = postService.readAllByCategory(pageable, "expert");
        User user = userService.readByUserNo(1).orElse(null);
        model.addAttribute("user", user);
        Page<PostDto> postDto = expertPostPage.map(PostDto::new);
        model.addAttribute("postDto", postDto);
        model.addAttribute("userList", user);
        return "community/expert_post";
    }

    @GetMapping("/postDetail.do")
    public String detail(Model model,
                         @RequestParam("postNo") int postNo
    ) {
        User user = new User();
        user.setId(1);
        Post post=postService.getPostByPostId(postNo);
        PostDto postDto=new PostDto(post);
        String formattedContent = convertNewlineToBr(post.getPostCont());
        model.addAttribute("formattedContent", formattedContent);// 서버에서 변환
        List<Comment> comments=commentService.readCommentByPostNo(postNo);
        int cntComment=commentService.getCommentCountByPostNo(postNo);
        Map<Integer, CommentLikeCountDTO> cntCommentLike = new HashMap<>();
        for (CommentLikeCountDTO dto : commentLikeService.  getLikesAndDislikesCount()) {
            cntCommentLike.put(dto.getCommentNo(), dto);
        }
        Map<Integer, Integer> replyCounts = new HashMap<>();
        for (Comment comment : comments) {
            if (comment.getParentNo() == null) { // 부모 댓글만 체크
                int count = commentService.getReplyCount(comment.getId());
                replyCounts.put(comment.getId(), count);
            }
        }// 변환된 내용 전달
        model.addAttribute("user", user);
        model.addAttribute("replyCounts", replyCounts);
        model.addAttribute("cntCommentLike", cntCommentLike);
        model.addAttribute("cntComment",cntComment);
        model.addAttribute("post", postDto);
        model.addAttribute("comments", comments);

        return "community/postDetail";
    }
    @PostMapping("/api/commentWrite")
    public ModelAndView commentWrite(@ModelAttribute Comment comment, RedirectAttributes redirectAttributes
    ) {
        int postNo=comment.getPostNo();
        User user = entityManager.find(User.class, 1);
        Hibernate.initialize(user);
        comment.setPostNo(comment.getPostNo());
        comment.setUserNo(user);
        comment.setCommentCreatedAt(Instant.now());
        comment.setCommentCont(comment.getCommentCont());
        Comment savedComment = commentRepository.save(comment);
        if(Objects.equals(savedComment.getCommentCont(), "")){
            return new ModelAndView("redirect:/community/postDetail.do?postNo=" + postNo);
        }
        commentRepository.flush();
        redirectAttributes.addFlashAttribute("savedComment", savedComment);
        return new ModelAndView("redirect:/community/postDetail.do?postNo=" + postNo);
    }
    @PostMapping("/api/childCommentWrite")
    @ResponseBody
    public ModelAndView childCommentWrite(@ModelAttribute Comment comment, RedirectAttributes redirectAttributes
    ) {
        int postNo=comment.getPostNo();
        User user = entityManager.find(User.class, 1);
        Hibernate.initialize(user);
        comment.setPostNo(comment.getPostNo());
        comment.setUserNo(user);
        comment.setCommentCreatedAt(Instant.now());
        comment.setParentNo(comment.getParentNo());
        String content = comment.getCommentCont().trim();
        if (content.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "댓글을 입력해주세요.");
            return new ModelAndView("redirect:/community/postDetail.do?postNo=" + postNo);
        }
        comment.setCommentCont(content);
        Comment childComment = commentRepository.save(comment);
        commentRepository.flush();
        redirectAttributes.addFlashAttribute("childComment", childComment);
        return new ModelAndView("redirect:/community/postDetail.do?postNo=" + postNo);
    }
    @PutMapping("/modifyComment")
    @ResponseBody
    public ResponseEntity<String> modifyComment(@RequestBody Comment comment) {
        try {
        // 댓글이 존재하는지 확인
        Optional<Comment> existingComment = commentRepository.findById(comment.getId());
        if (existingComment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 댓글을 찾을 수 없습니다.");
        }

        // 기존 내용과 비교하여 변경된 것이 없는 경우
        if (existingComment.get().getCommentCont().equals(comment.getCommentCont())) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("댓글 내용이 변경되지 않았습니다.");
        }
            commentService.modifyComment(comment);
            String redirectUrl = "/community/postDetail.do?postNo=" + comment.getPostNo();
            return ResponseEntity.ok(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정 중 오류 발생");
        }
    }

    @DeleteMapping("/deleteComment/{commentNo}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentNo) {
        try{
            commentService.removeCommentByCommentNo(commentNo);
            return ResponseEntity.ok("댓글이 삭제되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("modifyPost.do")
    public String modifyPost(Model model
            ,@RequestParam("postNo") int postNo) {
        Post post=postService.getPostByPostId(postNo);
        PostDto postDto=new PostDto(post);
        model.addAttribute("postDto", postDto);
        if(postDto.getId().equals(postNo)){
            return "community/modifyPost";
        }
        return "community/postDetail";
    }
    @PutMapping("/modifyPostAction.do")
    public ResponseEntity<String> modifyPostAction(@RequestBody PostDto postDto
    ) {
        int id = postDto.getId();
        String postCont = postDto.getPostCont();
        System.out.println(id);
        System.out.println(postCont);
        try {
            // 게시글 수정 서비스 호출
            postService.modifyPost(id, postCont);
            String redirectUrl = "/community/postDetail.do?postNo=" + id;
            ResponseEntity.ok("게시글이 성공적으로 수정되었습니다.");
            return ResponseEntity.ok(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 수정 중 오류 발생");
        }
    }
    @DeleteMapping("/post/{postNo}")
    public ResponseEntity<String> deletePost(@PathVariable int postNo){
        try{
            postService.deletePost(postNo);
            return ResponseEntity.ok("게시글이 삭제되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미 삭제되었거나 존재하지 않는 게시물입니다.");
        }

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
