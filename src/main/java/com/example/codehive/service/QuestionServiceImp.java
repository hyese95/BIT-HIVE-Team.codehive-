package com.example.codehive.service;

import com.example.codehive.entity.Question;
import com.example.codehive.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImp implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public List<Question> readByUser_IdAndQuestionStatus(Integer userNo, String questionStatus) {
        return questionRepository.findByUser_IdAndQuestionStatus(userNo, questionStatus);
    }

    @Override
    public Question readById(int questionNo) {
        return questionRepository.findById(questionNo);
    }
}