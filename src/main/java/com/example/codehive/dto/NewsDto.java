package com.example.codehive.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NewsDto {
    private String title;
    private String summary;
    private String contentUrl;
    private String publishedAt;
    private String publisher;
    private String thumbnailUrl;
}
