package com.example.codehive.service;

import com.example.codehive.entity.User;
import com.example.codehive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public List<User> findAllByUserNo(int userNo) {
        List<User> users = userRepository.findAllById(userNo);
        return users;
    }

    @Override
    public List<User> findAllByUserNameLike(String userName) {
        List<User> users = userRepository.findAllByUserIdLike(userName);
        return users;
    }

    @Override
    public User findNicknameByUserNo(int userNo) {
        User user = userRepository.findNicknameById(userNo);
        return user;
    }
}
