package com.example.codehive.repository;

import com.example.codehive.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Long> {

    List<Guide> findAll();
    Guide findById(long id);

    @Query("SELECT g FROM Guide g WHERE g.guideCont LIKE %:keyword% OR g.guideTitle LIKE %:keyword%")
    List<Guide> findByKeyword(String keyword);
}
