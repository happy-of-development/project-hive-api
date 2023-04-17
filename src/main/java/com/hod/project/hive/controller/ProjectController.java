package com.hod.project.hive.controller;

import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.common.vo.ApiResponse;
import com.hod.project.hive.entity.Project;
import com.hod.project.hive.entity.User;
import com.hod.project.hive.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/project")
    public ResponseEntity<ApiResponse> getProject(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam String status) {
        List<Project> projectList = projectService.getProject(beginDate, endDate, status);

        return ResponseEntity.ok(ApiResponseFactory.create(projectList));
    }
}
