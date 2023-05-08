package com.hod.project.hive.controller;

import com.hod.project.hive.common.exception.ApiCode;
import com.hod.project.hive.common.exception.ApiException;
import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.common.vo.ApiResponse;
import com.hod.project.hive.dto.UserRequest;
import com.hod.project.hive.dto.UserResponse;
import com.hod.project.hive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ApiResponse> addUser(@Validated @RequestBody UserRequest request) {
        userService.addUser(request);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUser(@NotBlank(message = "id가 입력되지 않았습니다.")
                                               @Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "id 형식이 잘못 되었습니다.") @RequestParam String id) {
        UserResponse response = userService.getUser(id);
        if (response == null) {
            throw new ApiException(ApiCode.USER_NOT_FOUND);
        }

        return ResponseEntity.ok(ApiResponseFactory.create(response));
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse> updateUser(@Validated(UserRequest.Update.class) @RequestBody UserRequest request) {
        userService.updateUser(request);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@NotBlank(message = "id가 입력되지 않았습니다.")
                                                  @Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "id 형식이 잘못 되었습니다.") @PathVariable String id) {
        userService.deleteUser(id);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }
}
