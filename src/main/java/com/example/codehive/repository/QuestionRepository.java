package com.example.codehive.repository;

import com.example.codehive.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByUser_IdAndQuestionStatus(Integer userNo, String questionStatus);
    Question findById(int questionNo);

}
