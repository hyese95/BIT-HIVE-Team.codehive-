package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "follows", schema = "bithive")
public class Follow {
    @EmbeddedId
    private FollowId id;

    @MapsId("followerUserNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "follower_user_no", nullable = false)
    private User followerUser;

    @MapsId("followingUserNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "following_user_no", nullable = false)
    private User followingUser;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "following_date")
    private Instant followingDate;

}