package com.example.codehive.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FollowId implements java.io.Serializable {
    private static final long serialVersionUID = 4520557389090256268L;
    @Column(name = "follower_user_no", nullable = false)
    private Integer followerUserNo;

    @Column(name = "following_user_no", nullable = false)
    private Integer followingUserNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FollowId entity = (FollowId) o;
        return Objects.equals(this.followerUserNo, entity.followerUserNo) &&
                Objects.equals(this.followingUserNo, entity.followingUserNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerUserNo, followingUserNo);
    }

}