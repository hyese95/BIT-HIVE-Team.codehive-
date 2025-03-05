package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "target_price_alerts", schema = "bithive")
public class TargetPriceAlert {
    @Id
    @Column(name = "target_price_alerts_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @Column(name = "market", nullable = false)
    private String market;

    @Column(name = "target_price", nullable = false)
    private Double targetPrice;

}