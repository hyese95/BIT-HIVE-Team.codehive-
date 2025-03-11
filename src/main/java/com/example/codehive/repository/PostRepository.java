package com.example.codehive.repository;

import com.example.codehive.entity.Post;
import com.example.codehive.entity.PostLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where (:category='%' or p.category = :category)" +
            " and p.postCont like %:keyword%")
    @EntityGraph(attributePaths = {"postLikes"})
    Page<Post> findByCategoryWithKeyword(String category, String keyword, Pageable pageable);


    @Query("select pl from PostLike pl where pl.post.id = :postNo ")
    List<PostLike> findLikesByPostNo(int postNo);

    Page<Post> findAllByCategory(Pageable pageable,String category);

}
