package com.hod.project.hive.dto;

import lombok.Data;

@Data
public class UserRequest {
    String id;
    String name;
    String password;
    String photo;
    String mobile;
    String email;
}
