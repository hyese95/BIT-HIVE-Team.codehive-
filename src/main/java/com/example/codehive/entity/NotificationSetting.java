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
    private Boolean volatilityYn;

    @ColumnDefault("0")
    @Column(name = "portfolio_yn")
    private Boolean portfolioYn;

    @ColumnDefault("0")
    @Column(name = "target_price_yn")
    private Boolean targetPriceYn;

    @ColumnDefault("0")
    @Column(name = "trade_yn")
    private Boolean tradeYn;

    @ColumnDefault("0")
    @Column(name = "like_yn")
    private Boolean likeYn;

    @ColumnDefault("0")
    @Column(name = "comment_yn")
    private Boolean commentYn;

    @ColumnDefault("0")
    @Column(name = "reply_yn")
    private Boolean replyYn;

    @ColumnDefault("0")
    @Column(name = "follower_yn")
    private Boolean followerYn;

}