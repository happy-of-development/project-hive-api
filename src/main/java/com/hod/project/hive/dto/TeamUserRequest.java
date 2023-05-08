package com.hod.project.hive.dto;

import lombok.Data;

import java.util.List;

@Data
public class  TeamUserRequest {
    private int id;
    private String name;
    private String desc;
    private String createdId;
    private String createdDate;
    private String modifiedId;
    private String modifiedDate;

    private List<TeamUserRequest.TeamUser> teamUserList;

    @Data
    public static class TeamUser {
        private String userId;
    }
}
