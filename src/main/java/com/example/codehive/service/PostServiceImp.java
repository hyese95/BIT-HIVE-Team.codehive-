package com.example.codehive.service;


import com.example.codehive.dto.PostDto;
import com.example.codehive.entity.Post;
import com.example.codehive.entity.User;
import com.example.codehive.repository.CommentRepository;
import com.example.codehive.repository.PostRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {
    PostRepository postRepository;
    UserService userService;
    EntityManager entityManager;
    CommentRepository commentRepository;
    @Override
    public Page<Post> readByCategoryWithKeyword(String category, String keyword, String sortType, Pageable pageable) {
        LocalDate startDate = switch (sortType) {
            case "daily" -> LocalDate.now().minusDays(1);
            case "weekly" -> LocalDate.now().minusDays(7);
            case "monthly" -> LocalDate.now().minusDays(30);
            default -> null;
        };
        System.out.println(sortType);
        System.out.println("startDate"+startDate);

        if (startDate == null) {

            return postRepository.findByCategoryWithKeyword(category, keyword, pageable);
        } else {
            System.out.println("시작@@@@@@@@@@@@@@@@@@@");
            System.out.println(postRepository.findByCategoryWithKeywordAndPeriod(category, keyword, startDate, pageable));
            return postRepository.findByCategoryWithKeywordAndPeriod(category, keyword, startDate, pageable);
        }

    }

    @Override
    public Page<Post> readByCategoryWithKeyword(String category, String keyword, Pageable pageable) {
        if (category.equals("all")) {
            category = "%";
        }
        return postRepository.findByCategoryWithKeyword(category, keyword, pageable);
    }

    @Override
    public Page<Post> readAllByCategory(Pageable pageable, String category) {
        Page<Post> posts;
        posts = postRepository.findByCategory(category,pageable);
        return posts;
    }

    @Override
    public Post getPostByPostId(int id) {
        Post posts;
        posts = postRepository.findPostById(id);
        return posts;
    }

    @Override
    @Transactional
    public void modifyPost(int postNo, String content) {
        Optional<Post> optionalPost = postRepository.findById(postNo);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setPostCont(content);  // 새로운 내용으로 업데이트
            entityManager.merge(post); // 변경 사항 저장
        } else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
    }

    @Override
    @Transactional
    public void deletePost(int postNo) {
        Optional<Post> optionalPost = postRepository.findById(postNo);
        if (optionalPost.isPresent()) {
            postRepository.deletePostByPostNo(postNo);
        }
        else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다");
        }
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post newPost = new Post();
        newPost.setPostCont(postDto.getPostCont());
//        newPost.setUser(user); // 현재 로그인한 사용자 정보 추가
        newPost.setPostCreatedAt(LocalDateTime.now());
        newPost.setCategory(postDto.getCategory());
        User user = userService.findAll().getFirst();
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        newPost.setUser(user);  // 실제 User 객체 설정
        newPost.setComment(new ArrayList<>());

        Post savedPost = postRepository.save(newPost);
        return new PostDto(savedPost);
    }

    @Override
    public Page<Post> readAll(PostDto postDto, Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> readByUserNo(Pageable pageable, int userNo) {
        return postRepository.findByUserNo(userNo, pageable);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            posts.add();
//        }
        return posts;
    }

    @Override
    public Page<PostDto> readAllDtoByCategory(PostDto.PostSearchRequestDto request) {
        Page<Post> page = postRepository.findByCategory(
                request.getCategory(),
                PageRequest.of(request.getPage(), request.getSize())
        );
        return page.map(PostDto::new);
    }

    @Override
    public List<PostDto> readPost(PostDto.FindPostDto postDto) {
        List<Post> posts=postRepository.findPostListById(
                postDto.getPostNo()
        );
        return posts.stream().map(PostDto::new).collect(Collectors.toList());
    }

}
