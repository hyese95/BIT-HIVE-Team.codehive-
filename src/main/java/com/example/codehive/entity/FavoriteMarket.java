package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favorite_markets", schema = "bithive")
public class FavoriteMarket {
    @Id
    @Column(name = "favorite_coin_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @Column(name = "market", nullable = false)
    private String market;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "list_no", nullable = false)
    private FavoriteMarketsFolder listNo;

    @Column(name = "sort_order", length = 10)
    private String sortOrder;

}