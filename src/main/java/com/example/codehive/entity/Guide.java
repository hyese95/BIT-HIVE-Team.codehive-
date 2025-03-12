package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "guides", schema = "bithive")
public class

Guide {
    @Id
    @Column(name = "guide_no", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "guide_cont")
    private String guideCont;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "guide_created_at", nullable = false)
    private Instant guideCreatedAt;

    @Column(name = "guide_title")
    private String guideTitle;

}