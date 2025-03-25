package com.example.codehive.service;

import com.example.codehive.dto.FollowDto;
import com.example.codehive.entity.Follow;
import com.example.codehive.entity.FollowId;
import com.example.codehive.entity.User;
import com.example.codehive.repository.FollowRepository;
import com.example.codehive.repository.PostRepository;
import com.example.codehive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private FollowRepository followRepository;

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
    @Override
    public int readPostsCount(int userNo) {
        return postRepository.countPostsByUserNo(userNo);
    }

    @Override
    public int readFollowersCount(int userNo) {
        return followRepository.countByFollowerUser_Id(userNo);
    }

    @Override
    public int readFollowingCount(int userNo) {
        return followRepository.countByFollowingUser_Id(userNo);
    }

    @Override
    public List<FollowDto.Follower> readFollowersByUserNo(Integer userNo, Pageable pageable) {
        return followRepository.findFollowersByUserNo(userNo, pageable);
    }

    @Override
    public List<FollowDto.Following> readFollowingsByUserNo(Integer userNo, Pageable pageable) {
        return followRepository.findFollowingsByUserNo(userNo, pageable);
    }

    @Transactional
    @Override
    public void unfollow(Integer followerUserNo, Integer followingUserNo) {
        followRepository.deleteByIdFollowerUserNoAndIdFollowingUserNo(followerUserNo, followingUserNo);
    }
}
