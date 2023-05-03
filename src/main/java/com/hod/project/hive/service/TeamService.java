package com.hod.project.hive.service;

import com.hod.project.hive.dto.TeamUserRequest;
import com.hod.project.hive.dto.TeamUserResponse;
import com.hod.project.hive.mapper.TeamMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TeamService {
    @Autowired
    private TeamMapper teamMapper;

    public void addTeam(TeamUserRequest request) {
        teamMapper.addTeam(request);

        int teamId = teamMapper.findLastInsertId();

        if (null == request.getTeamUserList()) {
            return;
        }

        for (TeamUserRequest.TeamUser teamUser : request.getTeamUserList()) {
            teamMapper.addTeamUser(teamId, teamUser.getId());
        }
    }

    public TeamUserResponse getTeam(String id) {
        TeamUserResponse team = new TeamUserResponse();

        List<TeamUserResponse.Team> teamList = teamMapper.findTeam(id);
        if (teamList == null) {
            return null;
        }

        for (TeamUserResponse.Team temp : teamList) {
            List<TeamUserResponse.TeamUser> user = teamMapper.findTeamUserList(temp.getId());
            if (user != null) {
                temp.setTeamUserList(user);
            }
        }
        team.setTeamList(teamList);

        return team;
    }

    public void updateTeam(TeamUserRequest request) {
        teamMapper.updateTeam(request);
    }

    public void deleteTeam(String id) {
        teamMapper.deleteTeamUser(id);
        teamMapper.deleteTeam(id);
    }
}
