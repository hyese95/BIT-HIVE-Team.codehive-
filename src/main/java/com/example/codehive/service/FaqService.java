package com.example.codehive.service;

import com.example.codehive.entity.Faq;

import java.util.List;

public interface FaqService {
    List<Faq> readAll();
    Faq readById(long id);
    List<Faq> readByKeyword(String keyword);
}
