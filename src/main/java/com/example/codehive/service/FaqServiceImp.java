package com.example.codehive.service;

import com.example.codehive.entity.Faq;
import com.example.codehive.repository.FaqRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FaqServiceImp implements FaqService {
    private final FaqRepository faqRepository;
    @Override
    public List<Faq> readAll() {
        return faqRepository.findAll();
    }

    @Override
    public Faq readById(long id) {
        return faqRepository.findById(id);
    }

    @Override
    public List<Faq> readByKeyword(String keyword) {
        return faqRepository.findByKeyword(keyword);
    }
}
