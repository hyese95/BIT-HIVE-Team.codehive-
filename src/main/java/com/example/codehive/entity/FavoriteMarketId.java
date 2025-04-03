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
public class FavoriteMarketId implements java.io.Serializable {
    private static final long serialVersionUID = -7965062304750508445L;
    @Column(name = "user_no", nullable = false)
    private Integer userNo;

    @Column(name = "market", nullable = false)
    private String market;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FavoriteMarketId entity = (FavoriteMarketId) o;
        return Objects.equals(this.market, entity.market) &&
                Objects.equals(this.userNo, entity.userNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(market, userNo);
    }

}