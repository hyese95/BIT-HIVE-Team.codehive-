package com.example.codehive.repository;

import com.example.codehive.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostNo(int postNo);
    @EntityGraph(attributePaths = {"childComments"})
//    @Query("SELECT c1.commentCont from Comment c1 INNER JOIN Comment c2 WHERE c2.id=c1.parentNo")
    Comment findWithChildCommentById(int commentNo);

    @EntityGraph(attributePaths = {"childComments"})
    Comment findById(int id);

    @Query("SELECT c.commentCont from Comment c where c.parentNo is null")
    Comment findComments(int id);

    List<Comment> findByParent_ParentNo(int parentNo);
}

