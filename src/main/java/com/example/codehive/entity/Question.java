package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "questions", schema = "bithive")
public class Question {
    @Id
    @Column(name = "question_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    @Lob
    @Column(name = "question_cont")
    private String questionCont;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "question_created_at", nullable = false)
    private Instant questionCreatedAt;

    @PrePersist
    protected void onCreate() {
        if (questionCreatedAt == null) {
            questionCreatedAt = Instant.now();
        }
    }

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "question_status", length = 20)
    private String questionStatus;

    @Column(name = "question_option", length = 20)
    private String questionOption;
}