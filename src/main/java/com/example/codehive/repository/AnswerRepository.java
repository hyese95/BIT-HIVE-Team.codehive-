package com.example.codehive.repository;

import com.example.codehive.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findByQuestion_Id(Integer questionNo);
}
