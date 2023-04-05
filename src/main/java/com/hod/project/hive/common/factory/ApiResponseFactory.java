package com.hod.project.hive.common.factory;

import com.hod.project.hive.common.vo.ApiResponse;

public class ApiResponseFactory {
    public static ApiResponse<Object> create(Object object) {
        ApiResponse<Object> result = new ApiResponse<>();
        result.setResult("200");
        result.setReason("성공");
        result.setData(object);
        return result;
    }
}