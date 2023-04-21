package com.hod.project.hive.controller;

import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.common.vo.ApiResponse;
import com.hod.project.hive.dto.ProjectManMonthResponse;
import com.hod.project.hive.dto.ProjectManMonthRequest;
import com.hod.project.hive.entity.Project;
import com.hod.project.hive.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/project")
    public ResponseEntity<ApiResponse> getProject(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam String status) {
        List<Project> projectList = projectService.getProject(beginDate, endDate, status);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectList", projectList);

        return ResponseEntity.ok(ApiResponseFactory.create(map));
    }

    @GetMapping("/project/detail")
    public ResponseEntity<ApiResponse> getProject(@RequestParam String id) {
//        List<Project> projectList = projectService.getProject(beginDate, endDate, status);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("projectList", projectList);

//        return ResponseEntity.ok(ApiResponseFactory.create(map));
        return null;
    }

    @GetMapping("/project/mm")
    public ResponseEntity<ApiResponse> getProjectManMonth(@RequestParam String year) {
        ProjectManMonthResponse response = projectService.getProjectManMonth(year);
        return ResponseEntity.ok(ApiResponseFactory.create(response));
    }

    @PutMapping("/project/mm")
    public ResponseEntity<ApiResponse> putProjectManMonth(@RequestBody ProjectManMonthRequest request) {
        projectService.updateProjectManMonth(request);
        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }
}
