package com.example.codehive.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "comments", schema = "bithive")
public class Comment {
    @Id
    @Column(name = "comment_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_no")
    private Integer parentNo;

    @Column(name = "post_no")
    private int postNo;

    // 🔹 게시글(Comment) - Post 관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_no", insertable = false, updatable = false)
    @ToString.Exclude
    @JsonBackReference("post-comment")
    private Post post;

    // 🔹 댓글(Comment) - User 관계 (N:1)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private User userNo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "comment_created_at", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd a hh시 MM분", shape = JsonFormat.Shape.STRING)
    private LocalDateTime commentCreatedAt;

    // 🔹 부모 댓글 관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parent_no", insertable = false, updatable = false)
    @JsonBackReference("parent-comment")
    @ToString.Exclude
    private Comment parent;

    @Column(name = "comment_cont", nullable = false)
    private String commentCont;

    // 🔹 자식 댓글 관계 (1:N)
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference("parent-comment")
    private Set<Comment> childComments;
}