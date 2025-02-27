package com.example.codehive.service;

import com.example.codehive.model.UsersModel;

import java.util.List;

public interface UsersService {
    List<UsersModel> readAll();
    UsersModel read(String userNo);
}
