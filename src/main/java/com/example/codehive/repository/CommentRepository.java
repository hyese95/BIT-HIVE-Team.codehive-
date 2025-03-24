package com.example.codehive.repository;

import com.example.codehive.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostNo(int postNo);
    @EntityGraph(attributePaths = {"childComments"})
//    @Query("SELECT c1.commentCont from Comment c1 INNER JOIN Comment c2 WHERE c2.id=c1.parentNo")
    Comment findWithChildCommentById(int parentNo);

    @EntityGraph(attributePaths = {"childComments"})
    Comment findById(int id);
//    @Query("select c.commentCont from Comment c where c.postNo=:postNo")
    List<Comment> findCommentContByPostNo(int postNo);
//    @Query("select c.commentCont from Comment c where c.postNo=:postNo and c.parentNo=:parentNo")
    List<Comment>  findCommentContByPostNoAndParentNo(int postNo,Integer parentNo);

    @Query("SELECT c.commentCont from Comment c where c.parentNo is null")
    Comment findComments(int id);

    List<Comment> findByParent_ParentNo(int parentNo);

    int countByPostNo(int postNo);
    int countChildCommentByPostNoAndParentNo(int postNo, int parentNo);

    @Transactional
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.postNo = :postNo")
    List<Comment> deleteCommentByPostNo(@Param("postNo") int postNo);

    int countByParentNo(int parentNo);

}

