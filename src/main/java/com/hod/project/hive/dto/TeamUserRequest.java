package com.hod.project.hive.dto;

import lombok.Data;

import java.util.List;

@Data
public class  TeamUserRequest {
    int id;
    String name;
    String desc;

    List<TeamUserRequest.TeamUser> teamUserList;

    @Data
    public static class TeamUser {
        String userName;
    }
}
