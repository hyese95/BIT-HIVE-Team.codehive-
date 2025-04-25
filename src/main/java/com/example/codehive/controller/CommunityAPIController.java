package com.example.codehive.controller;

import com.example.codehive.dto.CommentDto;
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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/community")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class CommunityAPIController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;
    private final PostRepository postRepository;
    private final Logger logger= LoggerFactory.getLogger(CommunityAPIController.class);
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

    @GetMapping("/posts/detail")
    public ResponseEntity<List<PostDto>> readPostDetail(@RequestParam int postNo) {
        PostDto.FindPostDto postDto=new PostDto.FindPostDto();
        postDto.setPostNo(postNo);
        List<PostDto> postDtos=postService.readPost(postDto);
        return ResponseEntity.ok().body(postDtos);
    }

    @GetMapping("/comments")
    public ResponseEntity<?> readComment(@RequestParam int postNo) {
        PostDto.FindPostDto postDto=new PostDto.FindPostDto();
        postDto.setPostNo(postNo);
        List<CommentDto> commentDto=commentService.readCommentDtoByPostNo(postNo);
        return ResponseEntity.ok().body(commentDto);
    }

    @DeleteMapping("/posts")
    public ResponseEntity<Void> deletePost(@RequestParam int postNo, @RequestParam int userNo) {
        if(userNo!=1){
            return ResponseEntity.unprocessableEntity().build();
        }
//        if(userNo!=loginUserNo){
//            return ResponseEntity.unprocessableEntity().build();
//        }
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
    public ResponseEntity<Void> deleteComment(@RequestParam int commentNo, @RequestParam int userNo) {
        if(userNo!=1){
            return ResponseEntity.unprocessableEntity().build();
        }
//        if(userNo!=loginUserNo){
//            return null;
//        }
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

    @PutMapping("/comments")
    public ResponseEntity<Void> modifyComment(@RequestBody Comment comment, @RequestParam int commentNo) {
        logger.info(comment.toString());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/comments")
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto, @RequestParam int postNo) {
        //        User user=userService.readByUserNo(loginUser).orElse(null) //추후 로그인 유저 넣으면 넣을 생각//Id 1 번 유저로 임의 설정
        User user = userService.readByUserNo(1).orElse(null);
        Post post=postService.getPostByPostId(postNo);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setPostNo(postNo);
        comment.setUserNo(user);
        comment.setCommentCont(commentDto.getCommentCont());
        System.out.println(comment.getCommentCont());
        System.out.println(commentDto.getCommentCont());
        if(commentDto.getParentNo()==null){
            comment.setParentNo(null);
        }else{comment.setParentNo(commentDto.getParentNo());}
        comment.setCommentCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        commentDto=new CommentDto(savedComment);
        System.out.println(commentDto);
        try{
            if (user == null || user.getId() == null) {
                throw new IllegalArgumentException("로그인 한 뒤에 이용하세요");
            }
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(409).build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(commentDto);
    }
}
