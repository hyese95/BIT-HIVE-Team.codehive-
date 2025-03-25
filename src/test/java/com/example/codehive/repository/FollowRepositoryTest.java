package com.example.codehive.repository;

import com.example.codehive.dto.FollowDto;
import com.example.codehive.entity.Follow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FollowRepositoryTest {
    @Autowired
    FollowRepository followRepository;


    @Test
    void countFollowingsByUserNo() {

    }

    @Test
    void countFollowersByUserNo() {
    }

    @Test
    void findFollowersByUserNo() {

    }

    @Test
    void countByFollowerUser_Id() {
        System.out.println(followRepository.countByFollowerUser_Id(1));
    }
}