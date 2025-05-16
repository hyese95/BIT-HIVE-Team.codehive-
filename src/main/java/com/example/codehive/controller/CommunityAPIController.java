package com.example.codehive.controller;

import com.example.codehive.dto.CommentAndUserLikeDto;
import com.example.codehive.dto.CommentDto;
import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Comment;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import com.example.codehive.jwt.JwtUtil;
import com.example.codehive.repository.CommentRepository;
import com.example.codehive.repository.PostRepository;
import com.example.codehive.security.CustomUserDetails;
import com.example.codehive.service.CommentLikeService;
import com.example.codehive.service.CommentService;
import com.example.codehive.service.PostService;
import com.example.codehive.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final JwtUtil jwtUtil;

    @GetMapping("/posts")
    public ResponseEntity<Page<PostDto>> readPost(
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
    public ResponseEntity<?> readComment(
            @RequestParam int postNo,
            HttpServletRequest request
    ) {
        String token= jwtUtil.resolveToken(request);
        if(token==null){
            List<CommentAndUserLikeDto> userLikeDtos=commentService.getCommentsWithUserLikeType(postNo,null);
            return ResponseEntity.ok(userLikeDtos);
        }
        String userId= jwtUtil.getUsername(token);
        User loginUser=userService.readByUserId(userId).orElseThrow();
        Integer loginUserNo=loginUser.getId();
        List<CommentAndUserLikeDto> userLikeDtos=commentService.getCommentsWithUserLikeType(postNo,loginUserNo);
        return ResponseEntity.ok(userLikeDtos);
    }

    @DeleteMapping("/posts")
    public ResponseEntity<Void> deletePost(@RequestParam int postNo
           ,@AuthenticationPrincipal CustomUserDetails userDetails
            ) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }
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
    public ResponseEntity<String> modifyPost(@RequestBody PostDto.ModifyPostRequest request
            ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }int loginUserNo=loginUser.getId();
        int postNo=request.getPostNo();
        Post post=postService.getPostByPostId(postNo);
        if(loginUserNo!=post.getUserNo()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 접근입니다.");
        }
        try {
            postService.modifyPost(request.getPostNo(),request.getPostCont());
            return ResponseEntity.ok("게시글이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 수정 중 오류 발생");
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestParam String category, @RequestBody PostDto postDto
            ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        if(userDetails==null){
            return ResponseEntity.badRequest().body("userDetail가 없어 반영할 수 없습니다.");
        }
        User loginUser=userService.readByUserId(userDetails.getUsername()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }
        Post post = new Post();
        post.setCategory(category);
        post.setPostCreatedAt(LocalDateTime.now());
        post.setUser(loginUser); // 반드시 저장 전에 User 세팅
        post.setUserNo(loginUser.getId());
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
    public ResponseEntity<Void> deleteComment(@RequestParam int commentNo
            ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }
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
    public ResponseEntity<?> modifyComment(@RequestBody CommentDto.ModifyRequest request
            ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }
        try {
            // 댓글이 존재하는지 확인
            Comment existingComment=commentService.readComment(request.getId());
            if (existingComment==null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 댓글을 찾을 수 없습니다.");
            }
            // 기존 내용과 비교하여 변경된 것이 없는 경우
            if (existingComment.getCommentCont().equals(request.getCommentCont())) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("댓글 내용이 변경되지 않았습니다.");
            }
            existingComment.setCommentCont(request.getCommentCont());
            Comment modifiedComment=commentRepository.save(existingComment);
            System.out.println(modifiedComment);
            return ResponseEntity.ok().body(new CommentDto(modifiedComment));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정 중 오류 발생");
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto, @RequestParam int postNo
            ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        User loginUser=userService.readByUserId(userDetails.getUser().getUserId()).orElse(null);
        if(loginUser==null){
            return ResponseEntity.badRequest().build();
        }
        Post post=postService.getPostByPostId(postNo);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setPostNo(postNo);
        comment.setUserNo(loginUser);
        comment.setCommentCont(commentDto.getCommentCont());
        if(commentDto.getParentNo()==null){
            comment.setParentNo(null);
        }else{comment.setParentNo(commentDto.getParentNo());}
        comment.setCommentCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        commentDto=new CommentDto(savedComment);
        System.out.println(commentDto);
        try{
            if ( loginUser.getId() == null) {
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
