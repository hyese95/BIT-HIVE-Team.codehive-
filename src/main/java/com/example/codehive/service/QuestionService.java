package com.example.codehive.service;

import com.example.codehive.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> readByUser_IdAndQuestionStatus(Integer userNo, String questionStatus);
    Question readById(int questionNo);
}
