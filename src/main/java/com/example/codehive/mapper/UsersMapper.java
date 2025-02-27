package com.example.codehive.mapper;

import com.example.codehive.model.UsersModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UsersMapper {
    List<UsersModel> findAll();
    UsersModel findByNo(String userNo);
}