package com.hod.project.hive.controller;

import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.dto.DashboardProjectPersonalResponse;
import com.hod.project.hive.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Slf4j
@Validated
@RestController
public class DashboardController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/dashboard/project")
    public ResponseEntity<?> getProjectManMonth(
            @Pattern(regexp="\\d{4}", message = "년도에 4자리 숫자만 입력 가능합니다.")
            @RequestParam(name = "year") String projectYear,

            @Size(min = 4, max = 8, message = "프로젝트 ID는 4 ~ 8자 범위만 가능합니다.")
            @RequestParam(required = false, name = "id") String projectId
    ) {
        DashboardProjectPersonalResponse dto = projectService.getDashboardProject(projectYear, projectId);
        return ResponseEntity.ok(ApiResponseFactory.create(dto));
    }

}
