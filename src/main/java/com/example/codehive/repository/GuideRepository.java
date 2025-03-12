package com.example.codehive.repository;

import com.example.codehive.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Long> {

    List<Guide> findAll();
    Guide findById(long id);
}
