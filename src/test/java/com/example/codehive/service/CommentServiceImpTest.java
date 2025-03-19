package com.example.codehive.service;

import com.example.codehive.entity.Comment;
import com.example.codehive.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImpTest {
    @Autowired
    private CommentRepository commentRepository;
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
}