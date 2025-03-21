package com.example.codehive.repository;

import com.example.codehive.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostLikeRepository extends JpaRepository<Post, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM PostLike pl WHERE pl.postNo = :postNo")
    void deletePostLikeByPostNo(@Param("postNo") int postNo);
}
