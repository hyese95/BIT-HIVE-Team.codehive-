package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "coin_transactions", schema = "bithive")
public class CoinTransaction {
    @Id
    @Column(name = "trans_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @Column(name = "market", nullable = false)
    private String market;

    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "transaction_cnt", nullable = false)
    private Double transactionCnt;

    @Column(name = "transaction_date", nullable = false)
    private Instant transactionDate;

    @Column(name = "transaction_state", length = 20)
    private String transactionState;

}