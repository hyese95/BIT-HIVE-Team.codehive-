package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "comment_likes", schema = "bithive")
public class CommentLike {
    @EmbeddedId
    private CommentLikeId id;

    @MapsId("userNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @MapsId("commentNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_no", nullable = false)
    private Comment commentNo;

    @Column(name = "like_type")
    private Boolean likeType;

}