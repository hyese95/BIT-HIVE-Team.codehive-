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
@Table(name = "exp_activity", schema = "bithive")
public class ExpActivity {
    @Id
    @Column(name = "activity_type", nullable = false, length = 100)
    private String activityType;

    @Column(name = "exp_amount")
    private Integer expAmount;

    @Column(name = "description")
    private String description;

}