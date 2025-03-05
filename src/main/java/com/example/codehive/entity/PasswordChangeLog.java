package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "password_change_logs", schema = "bithive")
public class PasswordChangeLog {
    @Id
    @Column(name = "password_log_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @Column(name = "changed_password", length = 50)
    private String changedPassword;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "change_date", nullable = false)
    private Instant changeDate;

}