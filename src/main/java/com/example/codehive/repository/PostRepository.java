package com.example.codehive.repository;

import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "select p from Post p where (:category='%' or p.category = :category) and p.postCont like %:keyword% order by p.postCreatedAt DESC",
            countQuery = "select count(p) from Post p where (:category='%' or p.category = :category) and p.postCont like %:keyword%")
    @EntityGraph(attributePaths = {"postLikes"})
    Page<Post> findByCategoryWithKeyword(String category, String keyword, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE (:category='%' or p.category = :category) " +
            "and p.postCont like %:keyword% " +
            "and p.postCreatedAt >= :startDate " +
            "ORDER BY SIZE(p.postLikes) DESC")
    Page<Post> findByCategoryWithKeywordAndPeriod(String category, String keyword,
                                                  @Param("startDate") LocalDate startDate,
                                                  Pageable pageable);

    @Query("select pl from PostLike pl where pl.post.id = :postNo ")
    List<PostLike> findLikesByPostNo(int postNo);

    @EntityGraph(attributePaths = {"postLikes","user"})
    Page<Post> findByCategory(String category, Pageable pageable);
//    이건 페이지 반환

    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.user.id = :Id")
    Page<Post> findByUserNo(@Param("Id") int Id, Pageable pageable);

    @Query("SELECT p from Post p where p.id = :postNo")
    Post findPostById(int postNo);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userNo")
    Integer countPostsByUserNo(@Param("userNo") Integer userNo);

    @Modifying
    @Transactional
    @Query("DELETE from Post p where p.id= :postNo")
    int deletePostByPostNo(int postNo);

    Optional<Post> findById(int id);

    List<Post> findPostListById(int id);
}
