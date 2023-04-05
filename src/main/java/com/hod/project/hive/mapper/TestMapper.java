package com.hod.project.hive.mapper;

import com.hod.project.hive.entity.TestObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface TestMapper {

    String findSomething();

    String findSomeById(@Param("id") int id);

    Map<String, Object> findObjectTypeMap();

    TestObject findObject();
}
