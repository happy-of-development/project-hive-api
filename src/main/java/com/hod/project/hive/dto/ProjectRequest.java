package com.hod.project.hive.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectRequest {
    private int id;
    private String name;
    private String pmId;
    private String beginDate;
    private String endDate;
    private String status;
    private List<ProjectUser> userList;

    @Data
    public static class ProjectUser {
        private String id;
    }
}
