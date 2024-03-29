package com.hod.project.hive.mapper;

import com.hod.project.hive.dto.TeamUserRequest;
import com.hod.project.hive.dto.TeamUserResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper {
    int addTeam(@Param("team") TeamUserRequest team);
    void addTeamUserList(@Param("team") TeamUserRequest team, @Param("list") List<TeamUserRequest.TeamUser> list);
    List<TeamUserResponse.Team> findTeam(@Param("id") String id);
    void updateTeam(@Param("team") TeamUserRequest team);
    List<TeamUserResponse.TeamUser> findTeamUserList(@Param("id") int id);
    void deleteTeam(@Param("id") String id);
    void deleteTeamUser(@Param("id") String id);
}
