package com.example.codehive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "faq", schema = "bithive")
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_no", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "faq_cont")
    private String faqCont;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "faq_created_at", nullable = false)
    private Instant faqCreatedAt;

    @Column(name = "faq_title")
    private String faqTitle;

}