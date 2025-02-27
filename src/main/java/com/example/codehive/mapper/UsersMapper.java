package com.example.codehive.mapper;

import com.example.codehive.model.UsersModel;

import java.util.List;

public interface UsersMapper {
    List<UsersModel> findAll();
}