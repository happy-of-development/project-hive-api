package com.hod.project.hive.service;

import com.hod.project.hive.dto.UserDto;
import com.hod.project.hive.entity.User;
import com.hod.project.hive.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void addUser(UserDto user) { userMapper.addUser(user); }

    public User getUser(String id) {
        return userMapper.findUser(id);
    }

    public void updateUser(UserDto user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(String id) {
        userMapper.deleteUser(id);
    }
}
