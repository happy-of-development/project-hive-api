package com.hod.project.hive.mapper;

import com.hod.project.hive.dto.TeamUserRequest;
import com.hod.project.hive.dto.TeamUserResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper {
    void addTeam(@Param("team") TeamUserRequest team);
    Integer findLastInsertId();
    void addTeamUser(@Param("teamId") int teamId, @Param("userName") String userName);
    TeamUserResponse findTeam(@Param("id") String id);
    void updateTeam(@Param("team") TeamUserRequest team);
    List<TeamUserResponse.TeamUser> findTeamUserList(@Param("id") String id);
    void deleteTeam(@Param("id") String id);
    void deleteTeamUser(@Param("id") String id);
}
