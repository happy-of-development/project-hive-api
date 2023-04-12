package com.hod.project.hive.mapper;

import com.hod.project.hive.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findUser(@Param("id") String id);
}

