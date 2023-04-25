package com.hod.project.hive.dto;

import com.hod.project.hive.entity.Project;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProjectDetailResponse extends Project {
    List<ProjectUser> userList;

    @Data
    public static class ProjectUser {
        String id;
        String name;
        String team;
        Float actualMm;
    }
}
