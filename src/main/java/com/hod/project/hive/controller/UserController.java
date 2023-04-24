package com.hod.project.hive.controller;

import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.common.vo.ApiResponse;
import com.hod.project.hive.dto.UserDto;
import com.hod.project.hive.entity.User;
import com.hod.project.hive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ApiResponse> addUser(@RequestBody UserDto user) {
        userService.addUser(user);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUser(@RequestParam String id) {
        User user = userService.getUser(id);
        if(user == null) {
            return ResponseEntity.ok(ApiResponseFactory.createError("404", "사용자 정보가 없습니다."));
        }

        return ResponseEntity.ok(ApiResponseFactory.create(user));
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse>  updateUser(@RequestBody UserDto user) {
        userService.updateUser(user);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }
}
