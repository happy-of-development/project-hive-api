package com.hod.project.hive.dto;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String name;
    private String teamName;
    private String photo;
    private String mobile;
    private String email;
}
