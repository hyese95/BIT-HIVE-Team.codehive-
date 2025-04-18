package com.example.codehive.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users", schema = "bithive")
public class User {
    @Id
    @Column(name = "user_no", nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false, length = 30)
    private String userId;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "nickname", nullable = false, length = 36)
    private String nickname;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @ColumnDefault("0")
    @Column(name = "privacy_agreements", nullable = false)
    private Boolean privacyAgreements = false;

    @ColumnDefault("0")
    @Column(name = "marketing_agreements", nullable = false)
    private Boolean marketingAgreements = false;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone="Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "profile_img_url")
    private String profileImgUrl;

    @Column(name = "nationality", nullable = false, length = 20)
    private String nationality;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @ColumnDefault("'DARK'")
    @Column(name = "theme", nullable = false, length = 10)
    private String theme;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "self_introduction", length = 150)
    private String selfIntroduction;

    @ColumnDefault("'USER'")
    @Column(name = "role", nullable = false, length = 30)
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Post> posts = new ArrayList<>();
}