package com.example.codehive.repository;

import com.example.codehive.dto.PostLikeDto;
import com.example.codehive.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {
    Optional<PostLike> findById(PostLikeId id);

    @Transactional
    @Modifying
    @Query("DELETE FROM PostLike pl WHERE pl.postNo = :postNo")
    void deletePostLikeByPostNo(@Param("postNo") int postNo);

    @Query("SELECT (p.postNo," +
            "CAST(SUM(CASE WHEN p.likeType = true THEN 1 ELSE 0 END)AS int), " +
            "CAST(SUM(CASE WHEN p.likeType = false THEN 1 ELSE 0 END)AS int)) " +
            "FROM PostLike p WHERE p.postNo = :postNo GROUP BY p.postNo")
    PostLikeDto getPostLikeAndDislikeCount(@Param("postNo") Integer postNo);

    @Query("SELECT (p.postNo," +
            "CAST(SUM(CASE WHEN p.likeType = true THEN 1 ELSE 0 END)AS int), " +
            "CAST(SUM(CASE WHEN p.likeType = false THEN 1 ELSE 0 END)AS int)) " +
            "FROM PostLike p WHERE p.postNo = :postNo GROUP BY p.postNo")
    List<PostLikeDto> getPostLikeAndDislike();
    int countByPostAndLikeType(Post post, boolean likeType);
}
