package com.hod.project.hive.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ApiResponse<T> {

    private String code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
