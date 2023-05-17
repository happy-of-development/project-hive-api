package com.hod.project.hive.controller;

import com.hod.project.hive.common.exception.ApiCode;
import com.hod.project.hive.common.exception.ApiException;
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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<ApiResponse> addProject(@Validated @RequestBody ProjectRequest request) {
        projectService.addProject(request);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @GetMapping("/project")
    public ResponseEntity<ApiResponse> getProject(@NotBlank(message = "프로젝트 시작 날짜를 입력해주세요.")
                                                  @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "프로젝트 시작 날짜 형식이 잘못 되었습니다.")
                                                  @RequestParam String beginDate,
                                                  @NotBlank(message = "프로젝트 종료 날짜를 입력해주세요.")
                                                  @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "프로젝트 종료 날짜 형식이 잘못 되었습니다.")
                                                  @RequestParam String endDate,
                                                  @Pattern(regexp = "INIT|PROGRESS|END|ALL", message = "status 값이 잘못 입력 되었습니다.")
                                                  @RequestParam String status) {
        if(beginDate.compareTo(endDate) >= 0) {
            throw new ApiException(ApiCode.BAD_REQUEST, "프로젝트 날짜가 잘못 입력되었습니다. 종료 날짜가 시작 날짜보다 앞 입니다.");
        }

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
    public ResponseEntity<ApiResponse> updateProject(@Validated @RequestBody ProjectRequest request) {
        projectService.updateProject(request);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @GetMapping("/project/mm")
    public ResponseEntity<ApiResponse> getProjectManMonth(
            @Pattern(regexp = "\\d{4}", message = "년도에 4자리 숫자만 입력 가능합니다.")
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
