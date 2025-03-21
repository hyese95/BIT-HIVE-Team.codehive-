package com.example.codehive.service;

import com.example.codehive.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> readByUserNo(int userNo);
    User findNicknameByUserNo(int userNo);
    void updateNickname(int userNo, String nickname);
    void updateSelfIntroduction(int userNo, String selfIntroduction);
    int readPostsCount(int userNo);
    int readFollowersCount(int userNo);
    int readFollowingCount(int userNo);


}
