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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/posts")
    public ResponseEntity<String> modifyPost(@RequestBody Post post,@RequestParam int postNo,@RequestParam int userNo) {
        postNo=post.getId();
        String postCont=post.getPostCont();
        if(userNo!=post.getUserNo()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 접근입니다.");
        }
        try {
            // 게시글 수정 서비스 호출
            postService.modifyPost(postNo, postCont);
            return ResponseEntity.ok("게시글이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 수정 중 오류 발생");
        }
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
    public ResponseEntity<String> modifyComment(@RequestBody Comment comment,@RequestParam int commentNo,@RequestParam int userNo) {
        if(userNo!=comment.getUserNo().getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 접근입니다.");
        }
        try {
            // 댓글이 존재하는지 확인
            Comment existingComment=commentService.readComment(commentNo);
            if (existingComment==null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 댓글을 찾을 수 없습니다.");
            }
            // 기존 내용과 비교하여 변경된 것이 없는 경우
            if (existingComment.getCommentCont().equals(comment.getCommentCont())) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("댓글 내용이 변경되지 않았습니다.");
            }
            commentService.modifyComment(comment);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정 중 오류 발생");
        }
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
