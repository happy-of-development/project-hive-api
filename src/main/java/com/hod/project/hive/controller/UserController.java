package com.hod.project.hive.controller;

import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.common.vo.ApiResponse;
import com.hod.project.hive.entity.User;
import com.hod.project.hive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUserProfile(@RequestParam String id) {
        User user = userService.getUser(id);
        if(user == null) {
            return ResponseEntity.ok(ApiResponseFactory.createError("404", "사용자 정보가 없습니다."));
        }

        return ResponseEntity.ok(ApiResponseFactory.create(user));
    }
}
