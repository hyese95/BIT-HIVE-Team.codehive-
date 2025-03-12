package com.example.codehive.service;

import com.example.codehive.entity.Guide;
import com.example.codehive.repository.GuideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GuideServiceImp implements GuideService {
    private final GuideRepository guideRepository;

    @Override
    public List<Guide> readAll() {
        return guideRepository.findAll();
    }

    @Override
    public Guide readById(long id) {
        return guideRepository.findById(id);
    }

    @Override
    public List<Guide> readByKeyword(String keyword) {
        return guideRepository.findByKeyword(keyword);
    }
}

