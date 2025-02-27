package com.example.codehive.service;

import com.example.codehive.mapper.UsersMapper;
import com.example.codehive.model.UsersModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersServiceImp implements UsersService {
    private final UsersMapper usersMapper;
    @Override
    public List<UsersModel> readAll() {
        return usersMapper.findAll();
    }

    @Override
    public UsersModel read(String userNo) {
        return usersMapper.findByNo(userNo);
    }
}
