package com.example.codehive.repository;

import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p where p.category=:category" +
            " and p.postCont like %:keyword%")
    List<Post> findByCategoryWithKeyword(String category, String keyword);

    @Query("select p from Post p where p.postCont like %:keyword%")
    List<Post> findByKeyword(String keyword);

    @Query("select pl from PostLike pl where pl.post.id = :postNo ")
    List<PostLike> findLikesByPostNo(int postNo);

    @Query("select p from Post p where p.category=:category order by p.postCreatedAt DESC")
    Page<Post> findAllByCategory(Pageable pageable,String category);

    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.user.id = :Id")
    List<Post> findByUserNo(@Param("Id") int Id);

    @Query("SELECT p from Post p JOIN fetch p.id where p.id = :postNo")
    Post findPostById(int postNo);
}

