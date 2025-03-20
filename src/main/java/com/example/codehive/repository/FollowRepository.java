package com.example.codehive.repository;

import com.example.codehive.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository  extends JpaRepository<Follow, Integer> {

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.followerUser.id = :userNo")
    Integer countFollowingsByUserNo(@Param("userNo") Integer userNo);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.followingUser.id = :userNo")
    Integer countFollowersByUserNo(@Param("userNo") Integer userNo);

}
