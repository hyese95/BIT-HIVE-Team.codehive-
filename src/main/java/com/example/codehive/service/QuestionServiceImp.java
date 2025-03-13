package com.example.codehive.service;

import com.example.codehive.entity.Question;
import com.example.codehive.entity.User;
import com.example.codehive.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImp {
    private final QuestionRepository questionRepository;
    public List<Question> readByUserNo(int userNo) {
        return questionRepository.findByUserNo(userNo);
    }
}
