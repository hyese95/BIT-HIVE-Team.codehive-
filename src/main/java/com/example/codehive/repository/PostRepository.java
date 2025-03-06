package com.example.codehive.repository;


import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.category=:category" +
            " and p.postCont like %:keyword%")
    List<Post> findByCategoryWithKeyword(String category, String keyword);

    @Query("select p from Post p where p.postCont like %:keyword%")
    List<Post> findByKeyword(String keyword);

    @Query("select pl from PostLike pl where pl.post.id = :postNo ")
    List<PostLike> findLikesByPostNo(int postNo);

}
