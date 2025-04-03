package com.example.codehive.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {
    private Integer id;
    private String questionCont;
    private String questionTitle;
    private String questionStatus;
    private String questionOption;
    private Instant questionCreatedAt;
    private Integer userNo;

}
