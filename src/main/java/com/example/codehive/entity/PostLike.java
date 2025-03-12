package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "post_likes", schema = "bithive")
@IdClass(PostLikeId.class)
public class PostLike {
    @Id
    @Column(name = "user_no", nullable = false)
    private Integer userNo;
    @Id
    @Column(name = "post_no", nullable = false)
    private Integer postNo;


    @MapsId("userNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    @ToString.Exclude
    private User users;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_no", nullable = false)
    @ToString.Exclude
    private Post post;

    @Column(name = "like_type")
    private Boolean likeType;

}