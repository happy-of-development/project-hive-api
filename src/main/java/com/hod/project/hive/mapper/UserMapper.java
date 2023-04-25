package com.hod.project.hive.mapper;

import com.hod.project.hive.dto.UserRequest;
import com.hod.project.hive.dto.UserResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void addUser(@Param("user") UserRequest user);
    UserResponse findUser(@Param("id") String id);
    void updateUser(@Param("user") UserRequest user);
    void deleteUser(@Param("id") String id);
}
