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
    public ResponseEntity<ApiResponse> addUser(@RequestBody @Validated UserRequest request) {
        userService.addUser(request);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUser(@RequestParam @NotBlank(message = "id가 입력되지 않았습니다.")
                                               @Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "id 형식이 잘못 되었습니다.") String id) {
        UserResponse response = userService.getUser(id);
        if (response == null) {
            throw new ApiException(ApiCode.USER_NOT_FOUND);
        }

        return ResponseEntity.ok(ApiResponseFactory.create(response));
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody @Validated(UserRequest.Update.class) UserRequest request) {
        userService.updateUser(request);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable @NotBlank(message = "id가 입력되지 않았습니다.")
                                                  @Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "id 형식이 잘못 되었습니다.") String id) {
        userService.deleteUser(id);

        return ResponseEntity.ok(ApiResponseFactory.create(null));
    }
}
