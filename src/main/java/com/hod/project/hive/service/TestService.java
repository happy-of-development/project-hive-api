package com.hod.project.hive.service;

import com.hod.project.hive.entity.TestObject;
import com.hod.project.hive.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public String getSomething() {
        return testMapper.findSomething();
    }

    public String getSome(int id) {
        return testMapper.findSomeById(id);
    }

    public Map<String, Object> getObjectTypeMap() {
        return testMapper.findObjectTypeMap();
    }

    public TestObject getObject() {
        return testMapper.findObject();
    }
}
