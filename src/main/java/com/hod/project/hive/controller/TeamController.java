package com.hod.project.hive.controller;

import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.common.vo.ApiResponse;
import com.hod.project.hive.dto.TeamUserRequest;
import com.hod.project.hive.dto.TeamUserResponse;
import com.hod.project.hive.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<ApiResponse> addTeam(@RequestBody TeamUserRequest request) {
        log.debug("addTeam : " + request.getId());
        teamService.addTeam(request);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @GetMapping("/team")
    public ResponseEntity<ApiResponse> getTeam(@RequestParam(required = false) String id) {
        TeamUserResponse team = teamService.getTeam(id);

        return ResponseEntity.ok(ApiResponseFactory.create(team));
    }

    @PutMapping("/team")
    public ResponseEntity<ApiResponse> updateTeam(@RequestBody TeamUserRequest request) {
        teamService.updateTeam(request);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @DeleteMapping("/team/{id}")
    public ResponseEntity<ApiResponse> deleteTeam(@PathVariable String id) {
        teamService.deleteTeam(id);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }
}
