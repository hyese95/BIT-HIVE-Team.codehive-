package com.example.codehive.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "comments", schema = "bithive")
public class Comment {
    @Id
    @Column(name = "comment_no", nullable = false)
    private Integer id;

    @Column(name = "parent_no")
    private Integer parentNo;

    @Column(name = "post_no")
    private int postNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_no", insertable = false, updatable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "comment_created_at", nullable = false)
    private Instant commentCreatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parent_no",insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    private Comment parent;

    @Column(name = "comment_cont", nullable = false)
    private String commentCont;

    @OneToMany(mappedBy = "parent",fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonBackReference
    private Set<Comment> childComments;

}