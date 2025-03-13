package com.example.codehive.service;

import com.example.codehive.entity.Answer;
import com.example.codehive.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AnswerServiceImp implements AnswerService {
    private final AnswerRepository answerRepository;
    @Override
    public Answer readByQuestion_Id(Integer questionNo) {
        return answerRepository.findByQuestion_Id(questionNo);
    }
}
