package com.example.codehive.repository;

import com.example.codehive.dto.CommentLikeCountDTO;
import com.example.codehive.entity.Comment;
import com.example.codehive.entity.CommentLike;
import com.example.codehive.entity.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {

    Optional<CommentLike> findCommentLikeById(CommentLikeId id);

    List<CommentLike> findByIdUserNoAndIdCommentNoIn(Integer userNo, List<Integer> commentNos);

    void deleteByIdAndLikeType(CommentLikeId id, boolean likeType);

    List<CommentLikeCountDTO> countLikesAndDislikesByComment(Comment comment);
}