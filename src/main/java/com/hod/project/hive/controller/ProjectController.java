package com.hod.project.hive.controller;

import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.common.vo.ApiResponse;

import com.hod.project.hive.dto.ProjectRequest;

import com.hod.project.hive.dto.ProjectManMonthResponse;
import com.hod.project.hive.dto.ProjectManMonthRequest;

import com.hod.project.hive.entity.Project;
import com.hod.project.hive.dto.ProjectDetailResponse;
import com.hod.project.hive.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<ApiResponse> addProject(@RequestBody ProjectRequest request) {
        projectService.addProject(request);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @GetMapping("/project")
    public ResponseEntity<ApiResponse> getProject(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam String status) {
        List<Project> projectList = projectService.getProject(beginDate, endDate, status);
        Map<String, Object> map = new HashMap<>();
        map.put("projectList", projectList);

        return ResponseEntity.ok(ApiResponseFactory.create(map));
    }

    @GetMapping("/project/detail")
    public ResponseEntity<ApiResponse> getProjectDetail(@RequestParam int id) {
       ProjectDetailResponse response = projectService.getProjectDetail(id);

       return ResponseEntity.ok(ApiResponseFactory.create(response));
    }

    @PutMapping("/project")
    public ResponseEntity<ApiResponse> updateProject(@RequestBody ProjectRequest request) {
       projectService.updateProject(request);

       return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @GetMapping("/project/mm")
    public ResponseEntity<ApiResponse> getProjectManMonth(
            @Pattern(regexp="\\d{4}", message = "년도에 4자리 숫자만 입력 가능합니다.")
            @RequestParam String year
    ) {
        ProjectManMonthResponse response = projectService.getProjectManMonth(year);
        return ResponseEntity.ok(ApiResponseFactory.create(response));
    }

    @PutMapping("/project/mm")
    public ResponseEntity<ApiResponse> putProjectManMonth(@Validated @RequestBody ProjectManMonthRequest request) {
        projectService.updateProjectManMonth(request);
        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }
}
