package com.example.codehive.repository;

import com.example.codehive.entity.Question;
import com.example.codehive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByUserNo(int userNo);
}
