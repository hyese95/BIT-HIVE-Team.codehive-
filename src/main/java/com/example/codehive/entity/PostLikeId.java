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
public class PostLikeId implements java.io.Serializable {
    private static final long serialVersionUID = -5028137642472229452L;
    @Column(name = "user_no", nullable = false)
    private Integer userNo;

    @Column(name = "post_no", nullable = false)
    private Integer postNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostLikeId entity = (PostLikeId) o;
        return Objects.equals(this.postNo, entity.postNo) &&
                Objects.equals(this.userNo, entity.userNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postNo, userNo);
    }

}