package com.example.codehive.controller;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Comment;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
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
    private final Logger logger= LoggerFactory.getLogger(CommunityRestController.class);

    @GetMapping("/read/{category}/{page}")
    public ResponseEntity<Page<PostDto>> read(
            @PathVariable String category,
            @PathVariable int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        PostDto.PostSearchRequestDto request=new PostDto.PostSearchRequestDto();
        request.setCategory(category);
        request.setPage(page);
        request.setSize(size);
        Page<PostDto> posts=postService.readAllDtoByCategory(request);
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping("/readPost/{category}")
    public ResponseEntity<Page<PostDto>> readPost(
            @PathVariable String category,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10") int size
    ){
        PostDto.PostSearchRequestDto request=new PostDto.PostSearchRequestDto();
        request.setCategory(category);
        request.setPage(page);
        request.setSize(size);
        Page<PostDto> posts=postService.readAllDtoByCategory(request);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/read/{postNo}/postDetail")
    public ResponseEntity<List<PostDto>> readPostDetail(@PathVariable int postNo) {
        PostDto.FindPostDto postDto=new PostDto.FindPostDto();
        postDto.setPostNo(postNo);
        List<PostDto> postDtos=postService.readPost(postDto);
        return ResponseEntity.ok().body(postDtos);
    }
    @GetMapping("/read/{postNo}/comments")
    public ResponseEntity<List<Comment>> readComment(@PathVariable int postNo) {
        PostDto.FindPostDto postDto=new PostDto.FindPostDto();
        postDto.setPostNo(postNo);
        List<Comment> comments=commentService.readCommentByPostNo(postNo);
        return ResponseEntity.ok().body(comments);
    }

    @DeleteMapping("/{postNo}/deletePost")
    public ResponseEntity<Void> deletePost(@PathVariable int postNo) {
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
    //localhost:/8888/rest/emp/144/mutate
//    mutant 돌연변이 => 데이터 조작
    @PutMapping("/modifyPost")
    public ResponseEntity<Void> modifyPost(@RequestBody Post post) {
        logger.info(post.toString());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/createPost/{category}")
    public ResponseEntity<Void> createPost(@RequestBody PostDto postDto,@PathVariable String category) {
        logger.info(postDto.toString());
//        User user=new User(); //추후 로그인 유저 넣으면 넣을 생각
//        user.setId(loginUser.getId())
        User user=new User();
        user.setId(1);//Id 1 번 유저로 임의 설정
        if (user.getUserId()==null) {
            throw new IllegalArgumentException("로그인 한 뒤에 이용하세요");
        }
        category=postDto.getCategory();
        postDto.setCategory(category);
        postDto.setPostCont(postDto.getPostCont());
        postDto.setPostCreatedAt(LocalDateTime.now());
        try{
            postService.createPost(postDto);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(409).build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{commentNo}/deleteComment")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentNo) {
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
    @PutMapping("/modifyComment")
    public ResponseEntity<Void> modifyComment(@RequestBody Comment comment) {
        logger.info(comment.toString());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/deleteComments")
    public ResponseEntity<Void> createComment(@RequestBody Comment comment) {
        logger.info(comment.toString());
        try{
            commentService.writeComments(comment);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(409).build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
