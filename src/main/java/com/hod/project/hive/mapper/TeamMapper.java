package com.hod.project.hive.mapper;

import com.hod.project.hive.dto.ProjectDetailResponse;
import com.hod.project.hive.dto.TeamRequest;
import com.hod.project.hive.dto.TeamUserResponse;
import com.hod.project.hive.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper {
    void addTeam(@Param("team") TeamRequest team);
    TeamUserResponse findTeam(@Param("id") String id);
    void updateTeam(@Param("team") TeamRequest team);

    List<TeamUserResponse.TeamUser> findTeamUserList(@Param("id") String id);
    void deleteTeam(@Param("id") String id);
    void deleteTeamUser(@Param("id") String id);
}
