package com.hod.project.hive.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDetail extends Project {
    List<ProjectUser> userList;
}
