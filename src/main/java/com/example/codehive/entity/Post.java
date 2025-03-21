package com.example.codehive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "posts", schema = "bithive")
public class Post {
    @Id
    @Column(name = "post_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    @JsonBackReference
    @ToString.Exclude
    private User user;

    @Lob
    @Column(name = "post_cont")
    private String postCont;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "post_created_at", nullable = false)
    private Instant postCreatedAt;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<PostLike> postLikes=new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    @JsonBackReference
    private List<Comment> comment;
}