package com.example.codehive.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false,insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    private User userNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_no", nullable = false,insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    private Comment commentNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_no",insertable=false, updatable=false,nullable = false)
    @ToString.Exclude
    @JsonManagedReference("comment-Like")
    private Comment comment;

    @Column(name = "like_type")
    private Boolean likeType;
}