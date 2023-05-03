package com.hod.project.hive.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class TeamUserResponse {
    private List <TeamUserResponse.Team> teamList;
    @Data
    public static class Team {
        private int id;
        private String name;
        private String desc;

        private List<TeamUserResponse.TeamUser> teamUserList;
    }
    @Data
    public static class TeamUser {
        private String id;
        private String name;
    }
}
