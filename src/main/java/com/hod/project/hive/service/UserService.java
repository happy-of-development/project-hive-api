package com.hod.project.hive.service;

import com.hod.project.hive.dto.UserRequest;
import com.hod.project.hive.dto.UserResponse;

import com.hod.project.hive.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addUser(UserRequest request) {
        userMapper.addUser(request);
    }

    public UserResponse getUser(String id) {
        return userMapper.findUser(id);
    }

    public void updateUser(UserRequest request) {
        userMapper.updateUser(request);
    }

    public void deleteUser(String id) {
        userMapper.deleteUser(id);
    }
}
