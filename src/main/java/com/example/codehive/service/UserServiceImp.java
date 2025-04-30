package com.example.codehive.service;

import com.example.codehive.dto.FollowDto;
import com.example.codehive.entity.Follow;
import com.example.codehive.entity.FollowId;
import com.example.codehive.entity.Role;
import com.example.codehive.entity.User;
import com.example.codehive.repository.FollowRepository;
import com.example.codehive.repository.PostRepository;
import com.example.codehive.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private FollowRepository followRepository;
    private EntityManager entityManager;

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
        return followRepository.countByFollowingUser_Id(userNo);

    }

    @Override
    public int readFollowingCount(int userNo) {
        return followRepository.countByFollowerUser_Id(userNo);
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

    @Override
    public boolean isFollowing(int userNo, int followingUserNo) {
        List<FollowDto.Following> list = followRepository.findAllFollowingsByUserNo(userNo);
        boolean isFollowing = false;
        for (FollowDto.Following following : list) {
            if (following.followingUserNo() == followingUserNo) {
                isFollowing = true;
                break;
            }
        }
        return isFollowing;
    }

    @Transactional
    @Override
    public void follow(int followerUserNo, int followingUserNo) {
        FollowId followId = new FollowId();
        followId.setFollowerUserNo(followerUserNo);
        followId.setFollowingUserNo(followingUserNo);
        Follow exsistFollow = entityManager.find(Follow.class, followId);
        if (exsistFollow != null) {
            throw new IllegalArgumentException("이미 팔로우 중입니다.");
        }
        User followUser = userRepository.findById(followerUserNo)
                .orElseThrow(() -> new IllegalArgumentException("팔로워 유저 없음"));
        User followingUser = userRepository.findById(followingUserNo)
                .orElseThrow(() -> new IllegalArgumentException("팔로잉할 유저 없음"));


        Follow follow = new Follow();
        follow.setId(followId);
        follow.setFollowingUser(followingUser);
        follow.setFollowerUser(followUser);
        follow.setFollowingDate(Instant.now());
        entityManager.persist(follow);
    }

    @Override
    public Optional<User> readByUserId(String userId) {
        return Optional.ofNullable(userRepository.findByUserId(userId));
    }

    @Override
    @Transactional
    public void register(User user) {
        user.setCreatedAt(LocalDateTime.now());
        if (userRepository.findByUserId(user.getUserId()) != null) {
            throw new IllegalArgumentException("이미 존재합니다");
        }

        // 비밀번호 해시 처리
        String hashPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPw);
        user.setRole(Role.USER);
        entityManager.persist(user);
        entityManager.flush();
    }

    // 중복체크
    @Override
    public boolean existsByUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
