package com.hod.project.hive.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private String result;
    private String reason;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
