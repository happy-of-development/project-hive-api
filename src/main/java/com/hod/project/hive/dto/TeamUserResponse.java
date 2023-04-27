package com.hod.project.hive.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class TeamUserResponse {
    int id;
    String name;
    String desc;

    List<TeamUserResponse.TeamUser> teamUserList;

    @Data
    public static class TeamUser {
        String userName;
    }
}
