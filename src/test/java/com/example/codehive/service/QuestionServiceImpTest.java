package com.example.codehive.service;

import com.example.codehive.entity.Question;
import com.example.codehive.entity.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class QuestionServiceImpTest {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    @Rollback(false)
    void registerQuestion() {
        Question question = new Question();
        Optional<User> userOpt= userService.readByUserNo(1);
        User user = userOpt.get();
        question.setUser(user);
        question.setQuestionTitle("제목");
        question.setQuestionCont("내용");
        question.setQuestionStatus("PENDING");
        question.setQuestionOption("account");
        questionService.registerQuestion(question);



    }
}