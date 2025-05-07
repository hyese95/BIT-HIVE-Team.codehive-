package com.example.codehive.repository;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.entity.CommentLike;
import com.example.codehive.entity.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {

    Optional<CommentLike> findCommentLikeById(CommentLikeId id);

    @Query("SELECT new com.example.codehive.dto.CommentLikeCountDTO( " +
            "cl.commentNo.id, " +
            "CAST(SUM(CASE WHEN cl.likeType = true THEN 1 ELSE 0 END) AS int), " +
            "CAST(SUM(CASE WHEN cl.likeType = false THEN 1 ELSE 0 END) AS int)) " +
            "FROM CommentLike cl " +
            "GROUP BY cl.commentNo.id")
    List<CommentLikeCountDTO> countLikesAndDislikesByComment();

    @Query("SELECT new com.example.codehive.dto.CommentLikeCountDTO(c.commentNo.id, " +
            "CAST(SUM(CASE WHEN c.likeType = true THEN 1 ELSE 0 END) AS int), " +
            "CAST(SUM(CASE WHEN c.likeType = false THEN 1 ELSE 0 END) AS int)) " +
            "FROM CommentLike c WHERE c.commentNo.id = :commentNo GROUP BY c.commentNo.id")
    CommentLikeCountDTO getCommentLikeCount(@Param("commentNo") Integer commentNo);

    void deleteByIdAndLikeType(CommentLikeId id, boolean likeType);
}