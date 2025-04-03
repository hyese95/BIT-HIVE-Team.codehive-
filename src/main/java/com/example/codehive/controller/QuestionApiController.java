package com.example.codehive.controller;


import com.example.codehive.dto.QuestionDto;
import com.example.codehive.entity.Question;
import com.example.codehive.entity.User;
import com.example.codehive.service.QuestionService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
@AllArgsConstructor
public class QuestionApiController {
    private final QuestionService questionService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> registerQuestion(@RequestBody QuestionDto questionDto) {

        try{
            Question question= new Question();
            question.setQuestionOption(questionDto.getQuestionOption());
            question.setQuestionTitle(questionDto.getQuestionTitle());
            question.setQuestionStatus(questionDto.getQuestionStatus());
            question.setQuestionCont(questionDto.getQuestionCont());
            Optional<User> userOpt=userService.readByUserNo(questionDto.getUserNo());
            User user=userOpt.get();
            question.setUser(user);
            questionService.registerQuestion(question);
            return ResponseEntity.ok().build();

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


}
