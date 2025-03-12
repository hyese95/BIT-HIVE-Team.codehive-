package com.example.codehive.repository;

import com.example.codehive.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {
    List<Faq> findAll();
    Faq findById(long id);

    @Query("SELECT f FROM Faq f WHERE f.faqCont LIKE %:keyword% OR f.faqTitle LIKE %:keyword%")
    List<Faq> findByKeyword(String keyword);
}
