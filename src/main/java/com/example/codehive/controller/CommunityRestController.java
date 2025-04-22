package com.example.codehive.controller;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Comment;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import com.example.codehive.repository.CommentRepository;
import com.example.codehive.repository.PostRepository;
import com.example.codehive.service.CommentLikeService;
import com.example.codehive.service.CommentService;
import com.example.codehive.service.PostService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/community")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class CommunityRestController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;
    private final PostRepository postRepository;
    private final Logger logger= LoggerFactory.getLogger(CommunityRestController.class);
    private final CommentRepository commentRepository;

    @GetMapping("/posts")
    public ResponseEntity<Page<PostDto>> read(
            @RequestParam String category,
            @RequestParam int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        PostDto.PostSearchRequestDto request=new PostDto.PostSearchRequestDto();
        request.setCategory(category);
        request.setPage(page);
        request.setSize(size);
        Page<PostDto> posts=postService.readAllDtoByCategory(request);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostDto>> readPostDetail(@RequestParam int postNo) {
        PostDto.FindPostDto postDto=new PostDto.FindPostDto();
        postDto.setPostNo(postNo);
        List<PostDto> postDtos=postService.readPost(postDto);
        return ResponseEntity.ok().body(postDtos);
    }
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> readComment(@RequestParam int postNo) {
        PostDto.FindPostDto postDto=new PostDto.FindPostDto();
        postDto.setPostNo(postNo);
        List<Comment> comments=commentService.readCommentByPostNo(postNo);
        return ResponseEntity.ok().body(comments);
    }

    @DeleteMapping("/posts")
    public ResponseEntity<Void> deletePost(@RequestParam int postNo) {
        try{
            postService.deletePost(postNo);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/modifyPost")
    public ResponseEntity<Void> modifyPost(@RequestBody Post post) {
        logger.info(post.toString());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestParam String category, @RequestBody PostDto postDto) {
//        User user=userService.readByUserNo(loginUser).orElse(null) //추후 로그인 유저 넣으면 넣을 생각//Id 1 번 유저로 임의 설정
        User user = userService.readByUserNo(1).orElse(null);
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("로그인 한 뒤에 이용하세요");
        }
        Post post = new Post();
        post.setCategory(category);
        post.setPostCreatedAt(LocalDateTime.now());
        post.setUser(user);
        post.setUserNo(user.getId());// 반드시 저장 전에 User 세팅
        post.setPostCont(postDto.getPostCont());
        System.out.println(user);
        if(post.getImgUrl()==null){
            post.setImgUrl(null);
        }else post.setImgUrl(post.getImgUrl());
        Post savedPost = postRepository.save(post); // ★ 저장 후 id 부여됨
        postDto = new PostDto(savedPost);
        postDto.setUserNo(post.getUserNo());// ★ 이제 DTO 생성 시 id 포함됨
        System.out.println(postDto);
        return ResponseEntity.ok().body(postDto);
    }
    @DeleteMapping("/comments")
    public ResponseEntity<Void> deleteComment(@RequestParam int commentNo) {
        try{
            commentService.removeCommentByCommentNo(commentNo);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
    //localhost:/8888/rest/emp/144/mutate
//    mutant 돌연변이 => 데이터 조작
    @PutMapping("/comments")
    public ResponseEntity<Void> modifyComment(@RequestBody Comment comment, @RequestParam int commentNo) {
        logger.info(comment.toString());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/comments")
    public ResponseEntity<?> createComment(@RequestBody String commentCont, @RequestParam int postNo) {
        //        User user=userService.readByUserNo(loginUser).orElse(null) //추후 로그인 유저 넣으면 넣을 생각//Id 1 번 유저로 임의 설정
        User user = userService.readByUserNo(1).orElse(null);
        Comment comment = new Comment();
        comment.setPostNo(postNo);
        comment.setUserNo(comment.getUserNo());
        comment.setParentNo(null);
        comment.setCommentCreatedAt(LocalDateTime.now());
        comment.setCommentCont(commentCont);
        int commentNo=comment.getId();
        System.out.println(comment);
        try{
            commentRepository.save(comment);
            if(commentNo!=comment.getId()){
                return null;
            }
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(409).build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(comment);
    }
}
