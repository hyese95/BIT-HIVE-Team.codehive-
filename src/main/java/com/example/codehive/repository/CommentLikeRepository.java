package com.example.codehive.repository;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.entity.CommentLike;
import com.example.codehive.entity.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {

    @Query("SELECT new com.example.codehive.dto.CommentLikeCountDTO( " +
            "cl.commentNo.id, " +
            "CAST(SUM(CASE WHEN cl.likeType = true THEN 1 ELSE 0 END) AS int), " +
            "CAST(SUM(CASE WHEN cl.likeType = false THEN 1 ELSE 0 END) AS int)) " +
            "FROM CommentLike cl " +
            "GROUP BY cl.commentNo.id")
    List<CommentLikeCountDTO> countLikesAndDislikesByComment();
}