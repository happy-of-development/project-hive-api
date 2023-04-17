package com.hod.project.hive.service;

import com.hod.project.hive.entity.Project;

import com.hod.project.hive.mapper.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    public List<Project> getProject(String beginDate, String endDate, String status) {
        return projectMapper.findProject(beginDate, endDate, status);
    }
}
