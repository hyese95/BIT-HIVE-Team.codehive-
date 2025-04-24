package com.example.codehive.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(name = "user_no", nullable = false)
    private Integer userNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false,insertable = false,updatable = false)
    @JsonBackReference
    @ToString.Exclude
    private User user;

    @Lob
    @Column(name = "post_cont")
    private String postCont;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "post_created_at", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd a hh시 MM분", shape = JsonFormat.Shape.STRING)
    private LocalDateTime postCreatedAt;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "post")
    @JsonBackReference
    @ToString.Exclude
    private List<PostLike> postLikes=new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference("post-comment")
    private List<Comment> comment = new ArrayList<>();
}