package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "posts", schema = "bithive")
public class Post {
    @Id
    @Column(name = "post_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @Lob
    @Column(name = "post_cont")
    private String postCont;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "post_created_at", nullable = false)
    private Instant postCreatedAt;

    @Column(name = "img_url")
    private String imgUrl;

}