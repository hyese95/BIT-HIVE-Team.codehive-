package com.example.codehive.repository;

import com.example.codehive.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Test
    @Transactional
    void findWithChildCommentById() {
        Comment comment = commentRepository.findWithChildCommentById(1);
        System.out.println(comment);

    }
    @Test
    @Transactional
    void findById(){
        System.out.println(commentRepository.findById(1).getChildComments());

    }

    @Test
    void findByParent_ParentNo() {

        List<Comment> list=commentRepository.findByParent_ParentNo(1);
        System.out.println(list);
    }

}