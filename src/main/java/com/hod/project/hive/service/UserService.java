package com.hod.project.hive.service;

import com.hod.project.hive.entity.User;
import com.hod.project.hive.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUser(String id) {
//        User user = new User();
//        user.setId("11111111");
//        user.setName("ㄴㅏ야");
//        user.setTeamName("우리팀");
//        user.setMobile("010-1112-2222");
//        user.setEmail("eungjulee77@gmail.com");
//
//        return user;

        return userMapper.findUser(id);
    }
}
