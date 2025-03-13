package com.example.codehive.service;

import com.example.codehive.entity.Answer;


public interface AnswerService {
    Answer readByQuestion_Id(Integer questionNo);
}
