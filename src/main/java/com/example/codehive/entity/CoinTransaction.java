package com.example.codehive.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
@Entity
@Table(name = "coin_transactions", schema = "bithive")
public class CoinTransaction {
    @Id
    @Column(name = "trans_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_no",nullable = false)
    private int userNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @Column(name = "market", nullable = false)
    private String market;

    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "transaction_cnt", nullable = false)
    private Double transactionCnt;

    @Column(name = "transaction_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone="Asia/Seoul")
    private Instant transactionDate;

    @Column(name = "transaction_state", length = 20)
    private String transactionState;

    @Transient
    private Double transactionAmount;
}