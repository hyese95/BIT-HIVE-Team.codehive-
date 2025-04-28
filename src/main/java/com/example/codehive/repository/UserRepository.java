package com.example.codehive.repository;

import com.example.codehive.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllById(int userNo);
    List<User> findAllByUserIdLike(String userId);
    User findNicknameById(int userNo);

    // 로그인 유저 조회용
    User findByUserId(String userId);

    boolean existsById(Integer id);
    boolean existsByUserId(String userId);
}
