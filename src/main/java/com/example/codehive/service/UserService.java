package com.example.codehive.service;

import com.example.codehive.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    List<User> findAllByUserNo(int userNo);
    List<User> findAllByUserNameLike(String userName);
}
