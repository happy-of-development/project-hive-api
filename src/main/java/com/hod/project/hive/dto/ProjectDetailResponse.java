package com.hod.project.hive.dto;

import com.hod.project.hive.entity.Project;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectDetailResponse extends Project {
    private List<ProjectUser> userList;

    @Data
    public static class ProjectUser {
        private String id;
        private String name;
        private String team;
        private Float actualMm;
    }
}
