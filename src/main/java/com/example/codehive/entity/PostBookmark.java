package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post_bookmarks", schema = "bithive")
public class PostBookmark {
    @EmbeddedId
    private PostBookmarkId id;

    @MapsId("userNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @MapsId("postNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_no", nullable = false)
    private Post postNo;

}