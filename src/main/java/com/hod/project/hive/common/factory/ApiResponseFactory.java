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

    public static ApiResponse<Object> createError(String result, String reason) {
        ApiResponse<Object> response = new ApiResponse<Object>();
        response.setResult(result);
        response.setReason(reason);

        return response;
    }
}
