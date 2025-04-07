package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "favorite_markets")
@IdClass(FavoriteMarketId.class)
public class FavoriteMarket {
    @Id
    @Column(name = "user_no", nullable = false)
    private Integer userNo;

    @Id
    @Column(name = "market", nullable = false)
    private String market;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false, insertable = false, updatable = false)
    private User user;

}