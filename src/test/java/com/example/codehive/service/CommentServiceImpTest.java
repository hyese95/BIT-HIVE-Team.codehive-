package com.example.codehive.service;

import com.example.codehive.entity.Comment;
import com.example.codehive.entity.User;
import com.example.codehive.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        Comment comment = commentService.readComment(1);
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
        System.out.println(commentRepository.findCommentByPostNo(postNo));
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
        comment.setCommentCont("앙 기모띠 살려줘");
        comment.setCommentCreatedAt(LocalDateTime.now());
        comment.setPostNo(91);
        comment.setUserNo(user);
        commentService.writeComments(comment);
    }

    @Test
    @Transactional
    void modifyComment() {
        Comment existingComment = commentService.readComment(62);
        System.out.println(existingComment);
        Comment comment = new Comment();
        User user = entityManager.find(User.class, 1);
        Hibernate.initialize(user);
        comment.setCommentCont("쉬바라");
        comment.setCommentCreatedAt(LocalDateTime.now());
        comment.setUserNo(user);
        comment.setId(62);
        commentService.modifyComment(comment);
        System.out.println(comment);
    }

    @Test
    void removeCommentByCommentNo() {
        commentService.removeCommentByCommentNo(97);
    }

    @Test
    @Transactional
    void writeChildComments() {
        Comment comment = new Comment();
        User user = entityManager.find(User.class, 1);
        Hibernate.initialize(user);
        comment.setCommentCont("앙 기모띠 살려줘");
        comment.setCommentCreatedAt(LocalDateTime.now());
        comment.setParentNo(69);
        comment.setPostNo(91);
        comment.setUserNo(user);
        commentService.writeChildComments(comment);
    }

    @Test
    @Transactional
    void readCommentDto() {
        System.out.println(commentService.readCommentDtoByPostNo(1));
    }


    @Test
    void modifyCommentDto() {

    }

    @Test
    @Transactional
    void getCommentsWithUserLikeType() {
        System.out.print(commentService.getCommentsWithUserLikeType(5,1).toString());
    }

    @Test
    @Transactional
    void toggleCommentLike() {
        entityManager.flush();
        entityManager.clear();
        System.out.println(commentService.toggleCommentLikeStatus(1,1,null));
        entityManager.flush();
        entityManager.clear();
        System.out.println(commentService.toggleCommentLikeStatus(1,1,false));
        entityManager.flush();
        entityManager.clear();
        System.out.println(commentService.toggleCommentLikeStatus(1,1,false));
        entityManager.flush();
        entityManager.clear();
        System.out.println(commentService.toggleCommentLikeStatus(1,1,true));
        entityManager.flush();
        entityManager.clear();
        System.out.println(commentService.toggleCommentLikeStatus(1,1,true));
        entityManager.flush();
        entityManager.clear();
        System.out.println(commentService.toggleCommentLikeStatus(1,1,true));
        entityManager.flush();
        entityManager.clear();
        System.out.println(commentService.toggleCommentLikeStatus(1,1,false));
    }
}