package com.hod.project.hive.service;

import com.hod.project.hive.dto.TeamUserRequest;
import com.hod.project.hive.dto.TeamUserResponse;
import com.hod.project.hive.mapper.TeamMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TeamService {
    @Autowired
    private TeamMapper teamMapper;

    private String getCurrentData() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);

        return str;
    }

    @Transactional
    public void addTeam(TeamUserRequest request) {
        // TODO: 로그인 후 수정 예정
        request.setCreatedId("1111111");
        request.setCreatedDate(getCurrentData());

        int teamId = teamMapper.addTeam(request);

        List<TeamUserRequest.TeamUser> teamUserList = request.getTeamUserList();
        if (teamUserList == null) {
            return;
        }

        request.setId(teamId);
        teamMapper.addTeamUserList(request, teamUserList);
    }

    public TeamUserResponse getTeam(String id) {
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

        TeamUserResponse team = new TeamUserResponse();
        team.setTeamList(teamList);

        return team;
    }

    public void updateTeam(TeamUserRequest request) {
        // TODO: 로그인 후 수정 예정
        request.setModifiedId("1111111");
        request.setModifiedDate(getCurrentData());

        teamMapper.updateTeam(request);
    }

    @Transactional
    public void deleteTeam(String id) {
        teamMapper.deleteTeamUser(id);
        teamMapper.deleteTeam(id);
    }
}
