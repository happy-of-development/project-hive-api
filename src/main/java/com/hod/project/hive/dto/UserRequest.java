package com.hod.project.hive.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String id;
    private String name;
    private String password;
    private String photo;
    private String mobile;
    private String email;
}
