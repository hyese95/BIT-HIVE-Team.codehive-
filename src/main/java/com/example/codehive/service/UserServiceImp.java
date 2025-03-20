package com.example.codehive.service;

import com.example.codehive.entity.User;
import com.example.codehive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> readByUserNo(int userNo) {
        return userRepository.findById(userNo);
    }

    @Override
    public User findNicknameByUserNo(int userNo) {
        User user = userRepository.findNicknameById(userNo);
        return user;
    }

    @Override
    public void updateNickname(int userNo, String nickname) {
        userRepository.findById(userNo).ifPresent(user -> {
            user.setNickname(nickname);
            userRepository.save(user);
        });
    }

    @Override
    public void updateSelfIntroduction(int userNo, String selfIntroduction) {
        userRepository.findById(userNo).ifPresent(user -> {
            user.setSelfIntroduction(selfIntroduction);
            userRepository.save(user);
        });
    }
}
