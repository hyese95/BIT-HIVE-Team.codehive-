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
        Comment comment = commentRepository.findComments(1);
        System.out.println(comment);
    }

    @Test
    void readChildComment() {
    }
}