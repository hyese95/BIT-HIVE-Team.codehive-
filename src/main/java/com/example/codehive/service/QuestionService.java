package com.example.codehive.service;

import com.example.codehive.entity.Question;
import com.example.codehive.entity.User;

import java.util.List;

public interface QuestionService {
    List<Question> readByUserNo(int userNo);
}
