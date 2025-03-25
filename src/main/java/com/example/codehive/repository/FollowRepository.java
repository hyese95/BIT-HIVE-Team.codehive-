package com.example.codehive.repository;

import com.example.codehive.dto.FollowDto;
import com.example.codehive.entity.Follow;
import com.example.codehive.entity.FollowId;
import com.example.codehive.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FollowRepository  extends JpaRepository<Follow, FollowId> {


    @Query("""
            SELECT new com.example.codehive.dto.FollowDto$Follower(
            f.followerUser.id,
            f.followerUser.nickname,
            f.followerUser.profileImgUrl,
            f.followerUser.name,
            f.followingDate
            )
             FROM Follow f
             WHERE f.followingUser.id = :userNo
             ORDER BY f.followingDate DESC
            """)
    List<FollowDto.Follower> findFollowersByUserNo(@Param("userNo") Integer userNo, Pageable pageable);

    @Query("""
            SELECT new com.example.codehive.dto.FollowDto$Following(
            f.followingUser.id,
            f.followingUser.nickname,
            f.followingUser.profileImgUrl,
            f.followingUser.name,
            f.followingDate
            )
             FROM Follow f
             WHERE f.followerUser.id = :userNo
             ORDER BY f.followingDate DESC
            """)
    List<FollowDto.Following> findFollowingsByUserNo(@Param("userNo") Integer userNo, Pageable pageable);

    int countByFollowerUser_Id(Integer userNo);

    int countByFollowingUser_Id(Integer userNo);

    @Transactional
    void deleteByIdFollowerUserNoAndIdFollowingUserNo(Integer followerUserNo, Integer followingUserNo);


}
