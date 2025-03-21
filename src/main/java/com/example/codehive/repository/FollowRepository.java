package com.example.codehive.repository;

import com.example.codehive.entity.Follow;
import com.example.codehive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository  extends JpaRepository<Follow, Integer> {

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.followerUser.id = :userNo")
    Integer countFollowingsByUserNo(@Param("userNo") Integer userNo);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.followingUser.id = :userNo")
    Integer countFollowersByUserNo(@Param("userNo") Integer userNo);


    @Query("SELECT f FROM Follow f WHERE f.followingUser.id=:userNo ORDER BY f.followingDate DESC")
    List<Follow> findFollowersByUserNo(@Param("userNo") Integer userNo);

    @Query("SELECT f FROM Follow f WHERE f.followerUser.id=:userNo ORDER BY f.followingDate DESC")
    List<Follow> findFollowingsByUserNo(@Param("userNo") Integer userNo);

}
