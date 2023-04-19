package com.hod.project.hive.controller;

import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.service.ProjectService;
import com.hod.project.hive.dto.DashboardPersonalProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DashboardController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/dashboard/project")
    public ResponseEntity<?> getProjectManMonth(@RequestParam String year, @RequestParam(required = false) String id) {
        DashboardPersonalProjectDto dashboardPersonalProjectDto = projectService.getDashboardProject(year, id);
        return ResponseEntity.ok(ApiResponseFactory.create(dashboardPersonalProjectDto));
    }

}
