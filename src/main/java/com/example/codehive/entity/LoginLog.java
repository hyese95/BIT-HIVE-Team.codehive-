package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "login_logs", schema = "bithive")
public class LoginLog {
    @Id
    @Column(name = "login_log_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "login_date", nullable = false)
    private Instant loginDate;

}