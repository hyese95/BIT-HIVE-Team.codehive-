package com.example.codehive.service;

import com.example.codehive.entity.Guide;

import java.util.List;

public interface GuideService {
    List<Guide> readAll();
    Guide readById(long id);
}
