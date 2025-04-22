package com.example.codehive.service;

import com.example.codehive.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImpTest {
    @Autowired
    private UserService userService;


//user_no
//user_id
//password
//nickname
//email
//phone
//privacy_agreements
//marketing_agreements
//created_at
//profile_img_url
//nationality
//gender
//theme
//birth_date
//name
//self_introduction
//role
    @Test
    void findAllByUserNameLike() {
    }

    @Test
    void isFollowing() {
        boolean isFollowing=userService.isFollowing(1,6);
        System.out.println(isFollowing);
    }

    @Test
    @Transactional
    @Rollback(false)
    void follow() {
        userService.follow(1,3);
    }



    @Test
    @Transactional
    void findAll() {
        System.out.println(userService.findAll());
    }

    @Test
    void updateNickname() {
    }

    @Test
    @Transactional
    void readByUserNo() {
        System.out.println(userService.readByUserNo(1));
    }

}