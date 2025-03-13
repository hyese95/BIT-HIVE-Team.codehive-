package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "answers", schema = "bithive")
public class Answer {
    @Id
    @Column(name = "answer_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_no", nullable = false)
    private Question question;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "answer_created_at", nullable = false)
    private Instant answerCreatedAt;

    @Lob
    @Column(name = "answer_cont")
    private String answerCont;

}