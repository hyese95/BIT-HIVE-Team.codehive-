package com.example.codehive.repository;

import com.example.codehive.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {
    List<Faq> findAll();
    Faq findById(long id);
}
