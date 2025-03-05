package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "comments", schema = "bithive")
public class Comment {
    @Id
    @Column(name = "comment_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_no", nullable = false)
    private Post postNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "comment_created_at", nullable = false)
    private Instant commentCreatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parent_no", nullable = false)
    private Comment parentNo;

    @Column(name = "comment_cont", nullable = false)
    private String commentCont;

}