package com.hod.project.hive.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
    String name;
    String pmId;
    String beginDate;
    String endDate;
    String status;
    List<ProjectUser> userList;

      @Data
      public static class ProjectUser {
          String id;
      }
}
