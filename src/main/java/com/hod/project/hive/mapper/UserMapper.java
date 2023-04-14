package com.hod.project.hive.mapper;

import com.hod.project.hive.dto.UserDto;
import com.hod.project.hive.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void addUser(@Param("user") com.hod.project.hive.dto.UserDto user);
    User findUser(@Param("id") String id);
    void updateUser(@Param("user") com.hod.project.hive.dto.UserDto user);
}
