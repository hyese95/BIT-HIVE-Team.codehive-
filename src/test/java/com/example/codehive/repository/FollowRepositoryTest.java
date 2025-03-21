package com.example.codehive.repository;

import com.example.codehive.entity.Follow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        List<Follow> list= followRepository.findFollowersByUserNo(1);
        System.out.println(list.size());
    }
}