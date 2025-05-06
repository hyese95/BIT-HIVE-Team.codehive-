package com.example.codehive.repository;

import com.example.codehive.dto.CommentDto;
import com.example.codehive.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    @Query("SELECT c FROM Comment c WHERE c.postNo=:postNo order by c.commentCreatedAt DESC ")
    List<Comment> findCommentByPostNo(int postNo);
//    @Query("select c.commentCont from Comment c where c.postNo=:postNo and c.parentNo=:parentNo")
    List<Comment>  findCommentContByPostNoAndParentNo(int postNo,Integer parentNo);
    @Query("SELECT c.commentCont from Comment c where c.parentNo is null")
    Comment findComments(int id);
    List<Comment> findByParent_ParentNo(int parentNo);
    int countByPostNo(int postNo);
    int countChildCommentByPostNoAndParentNo(int postNo, int parentNo);
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.id = :commentNo")
    void deleteCommentByPostNo(@Param("commentNo") int commentNo);
    int countByParentNo(int parentNo);
    @Query( value = "SELECT " +
            "    c.id AS commentNo, " +
            "    c.comment_cont," +
            "    c.parent_no AS parentNo , " +
            "    CAST(SUM(CASE WHEN cl.like_type = TRUE THEN 1 ELSE 0 END) AS int) AS likeCount, " +
            "    CAST(SUM(CASE WHEN cl.like_type = FALSE THEN 1 ELSE 0 END) AS int) AS dislikeCount, " +
            "        SELECT cl2.like_type" +
            "        FROM comment_likes cl2" +
            "        WHERE cl2.comment_id = c.id AND cl2.user_no = :userNo " +
            "        LIMIT 1" +
            "    ) AS userLikeType " +
            "FROM comment c" +
            "LEFT JOIN comment_like cl ON c.id = cl.comment_id " +
            "WHERE c.post_id = :postNo " +
            "GROUP BY c.id " +
            "ORDER BY c.id ASC",nativeQuery=true)
    List<CommentDto.CommentDtoRequest> findCommentsWithUserLike(@Param("postNo") int postNo,
                                              @Param("userNo") int userNo);
    //유저 별로 누른 like 추출하기 위해 만듬
//    @Modifying
//    @Query("INSERT INTO Comment (postNo,userNo,commentCreatedAt,parentNo,commentCont) values (?,?,?,?,?)")
//    List<Comment> createChildComments(int commentNo);
}

