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
public class CommentLikeId implements java.io.Serializable {
    private static final long serialVersionUID = -618883628258142597L;
    @Column(name = "user_no", nullable = false)
    private Integer userNo;

    @Column(name = "comment_no", nullable = false)
    private Integer commentNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommentLikeId entity = (CommentLikeId) o;
        return Objects.equals(this.commentNo, entity.commentNo) &&
                Objects.equals(this.userNo, entity.userNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentNo, userNo);
    }

}