package com.example.codehive.controller;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Comment;
import com.example.codehive.entity.Post;
import com.example.codehive.service.CommentLikeService;
import com.example.codehive.service.CommentService;
import com.example.codehive.service.PostService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("read/{category}")
    public ResponseEntity<Page<Post>> read(
            @PathVariable String category,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("UTC")); // 또는 시스템 타임존
        String dateStr = LocalDateTime.now().format(formatter);
        Instant instant = LocalDateTime.parse(dateStr, formatter).atZone(ZoneId.of("UTC")).toInstant();
        PageRequest pageRequest=PageRequest.of(page-1, size);
        Page<Post> postPage=postService.readAllByCategory(pageRequest, category);
        postPage.stream().forEach(post->{
            post.setPostCreatedAt(instant);
        });
        return ResponseEntity.ok(postPage);
    }

    @GetMapping("read/{postNo}/postDetail")
    public ResponseEntity<Post> read(@PathVariable int postNo) {
        Post post=postService.getPostByPostId(postNo);
        List<Comment> comments=commentService.readCommentByPostNo(postNo);
        int cntComment=commentService.getCommentCountByPostNo(postNo);
        Map<Integer, CommentLikeCountDTO> cntCommentLike = new HashMap<>();
        for (CommentLikeCountDTO dto : commentLikeService.getLikesAndDislikesCount()) {
            cntCommentLike.put(dto.getCommentNo(), dto);
        }
        Map<Integer, Integer> replyCounts = new HashMap<>();
        for (Comment comment : comments) {
            if (comment.getParentNo() == null) { // 부모 댓글만 체크
                int count = commentService.getReplyCount(comment.getId());
                replyCounts.put(comment.getId(), count);
            }
        }
        if(post==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
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
    @PostMapping("/createPost")
    public ResponseEntity<Void> createPost(@RequestBody PostDto postDto) {
        logger.info(postDto.toString());
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
