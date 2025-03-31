package com.example.codehive.service;

import com.example.codehive.entity.Comment;
import com.example.codehive.entity.User;
import com.example.codehive.repository.CommentRepository;
import com.example.codehive.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImpTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    void readComment() {
        List<Comment> comment = commentRepository.findByPostNo(1);
        System.out.println(comment);
    }

    @Test
    @Transactional
    void readChildComment() {
        Comment comment = commentRepository.findWithChildCommentById(1);
        System.out.println(comment);
    }

    @Test
    @Transactional
    void getCommentCountByPostNo() {
        int postNo = 1;
        int cntCont=commentRepository.countByPostNo(postNo);
        System.out.println(cntCont);
    }

    @Test
    @Transactional
    void getChildCommentCountByPostNoAndParentNo() {
        int postNo = 1;
        int parentNo = 1;
        int childCont=commentRepository.countChildCommentByPostNoAndParentNo(postNo,parentNo);
        System.out.println(childCont);
    }

    @Test
    @Transactional
    void readCommentByPostNo() {
        int postNo = 1;
        System.out.println(commentRepository.findCommentContByPostNo(postNo));
    }

    @Test
    @Transactional(readOnly = true)
    void readCommentByPostNoAndParentNo() {
        int postNo = 1;
        int parentNo = 1;
        System.out.println(commentRepository.findCommentContByPostNoAndParentNo(postNo,parentNo));
    }

    @Test
    @Transactional
    void writeComment() {
        Comment comment = new Comment();
        User user = entityManager.find(User.class, 1);
        Hibernate.initialize(user);
        System.out.println(user.getId());
        comment.setCommentCont("앙 기모띠 살려줘");
        comment.setCommentCreatedAt(Instant.now());
        comment.setPostNo(91);
        comment.setUserNo(user);
        commentService.writeComments(comment);
    }

    @Test
    void modifyComment() {
        Comment comment = new Comment();
        User user = entityManager.find(User.class, 1);
        Hibernate.initialize(user);
        comment.setCommentCont("쉬바라");
        comment.setCommentCreatedAt(Instant.now());
        comment.setUserNo(user);
        comment.setId(94);
        commentService.modifyComment(comment);
        System.out.println(comment);
    }
}