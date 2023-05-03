package com.hod.project.hive.service;

import com.hod.project.hive.dto.TeamUserRequest;
import com.hod.project.hive.dto.TeamUserResponse;
import com.hod.project.hive.mapper.TeamMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeamService {
    @Autowired
    TeamMapper teamMapper;

    public void addTeam(TeamUserRequest request) {
        teamMapper.addTeam(request);

        int teamId = teamMapper.findLastInsertId();

        if (null == request.getTeamUserList()) {
            return;
        }

        for (TeamUserRequest.TeamUser teamUser : request.getTeamUserList()) {
            teamMapper.addTeamUser(teamId, teamUser.getUserName());
        }
    }

    public TeamUserResponse getTeam(String id) {
        TeamUserResponse teamUser = teamMapper.findTeam(id);
        if (teamUser == null) {
            return null;
        }
        List<TeamUserResponse.TeamUser> user = teamMapper.findTeamUserList(id);
        if (user != null) {
            teamUser.setTeamUserList(user);
        }
        return teamUser;
    }

    public void updateTeam(TeamUserRequest request) {
        teamMapper.updateTeam(request);
    }

    public void deleteTeam(String id) {
        teamMapper.deleteTeamUser(id);
        teamMapper.deleteTeam(id);
    }
}
