package com.hod.project.hive.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProjectDetail extends Project {
    List<ProjectUser> userList;

    @Data
    public static class ProjectUser {
        String id;
        String name;
        String team;
        Float actualMm;
    }
}
