package com.example.codehive.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "notification_settings", schema = "bithive")
public class NotificationSetting {
    @Id
    @Column(name = "user_no", nullable = false)
    private Integer id;

    @ColumnDefault("0")
    @Column(name = "volatility_yn")
    private boolean volatilityYn;

    @ColumnDefault("0")
    @Column(name = "portfolio_yn")
    private boolean portfolioYn;

    @ColumnDefault("0")
    @Column(name = "target_price_yn")
    private boolean targetPriceYn;

    @ColumnDefault("0")
    @Column(name = "trade_yn")
    private boolean tradeYn;

    @ColumnDefault("0")
    @Column(name = "like_yn")
    private boolean likeYn;

    @ColumnDefault("0")
    @Column(name = "comment_yn")
    private boolean commentYn;

    @ColumnDefault("0")
    @Column(name = "reply_yn")
    private boolean replyYn;

    @ColumnDefault("0")
    @Column(name = "follower_yn")
    private boolean followerYn;

}