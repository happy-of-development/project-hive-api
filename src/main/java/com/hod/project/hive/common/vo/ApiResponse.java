package com.hod.project.hive.common.vo;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String result;
    private String reason;
    private T data;
}
