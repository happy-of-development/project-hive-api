package com.hod.project.hive.service;

import com.hod.project.hive.dto.ProjectDetailResponse;
import com.hod.project.hive.dto.TeamRequest;
import com.hod.project.hive.dto.TeamUserResponse;
import com.hod.project.hive.entity.Project;
import com.hod.project.hive.entity.Team;
import com.hod.project.hive.mapper.TeamMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeamService {
    @Autowired
    TeamMapper teamMapper;

    public void addTeam(TeamRequest request) {
        teamMapper.addTeam(request);
    }

    public TeamUserResponse getTeam(String id) {
        TeamUserResponse teamUser = new TeamUserResponse();

        teamUser = teamMapper.findTeam(id);

        List<TeamUserResponse.TeamUser> user = teamMapper.findTeamUserList(id);
        teamUser.setTeamUserList(user);

        return teamUser;
    }

    public void updateTeam(TeamRequest request) {
        teamMapper.updateTeam(request);
    }

    public void deleteTeam(String id) {
        teamMapper.deleteTeamUser(id);
        teamMapper.deleteTeam(id);
    }
}
