package com.hod.project.hive.service;

import com.hod.project.hive.entity.Project;

import com.hod.project.hive.entity.ProjectDetail;
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
        List<Project> projectList =  projectMapper.findProject(beginDate, endDate, status);
        for(Project project : projectList) {
           project.setExpectMm(Float.toString(projectMapper.findProjectTotalMm(project.getId(), "EXPECT", null)));
           project.setActualMm(Float.toString(projectMapper.findProjectTotalMm(project.getId(), "ACTUAL", null)));
        }

        return projectList;
    }

    public ProjectDetail getProjectDetail(String id) {
        ProjectDetail detail = projectMapper.findProjectDetail(id);
        detail.setExpectMm(Float.toString(projectMapper.findProjectTotalMm(id, "EXPECT", null)));
        detail.setActualMm(Float.toString(projectMapper.findProjectTotalMm(id, "ACTUAL", null)));

        List<ProjectDetail.ProjectUser> userList = projectMapper.findUserList(id);
        for(ProjectDetail.ProjectUser user : userList) {
            user.setActualMm(projectMapper.findProjectTotalMm(id, "ACTUAL", user.getId()));
        }

        detail.setUserList(userList);

        return detail;
    }
}
