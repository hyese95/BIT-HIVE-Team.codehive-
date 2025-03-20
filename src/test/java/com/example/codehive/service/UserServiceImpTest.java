package com.example.codehive.service;

import com.example.codehive.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
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
}