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
    @JsonProperty("title")
    private String title;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("content_url")
    private String contentUrl;

    @JsonProperty("published_at")
    private String publishedAt;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
}
