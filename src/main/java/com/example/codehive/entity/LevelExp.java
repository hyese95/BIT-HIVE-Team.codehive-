package com.example.codehive.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "level_exp", schema = "bithive")
public class LevelExp {
    @Id
    @Column(name = "level", nullable = false)
    private Integer id;

    @Column(name = "required_exp")
    private Integer requiredExp;

}