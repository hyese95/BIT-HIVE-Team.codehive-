package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "chatting_logs", schema = "bithive")
public class ChattingLog {
    @Id
    @Column(name = "chat_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "chat_created_at", nullable = false)
    private Instant chatCreatedAt;

    @Column(name = "content", nullable = false)
    private String content;

}