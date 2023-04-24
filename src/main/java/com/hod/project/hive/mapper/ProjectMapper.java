package com.hod.project.hive.mapper;

import com.hod.project.hive.dto.ProjectDto;
import com.hod.project.hive.entity.Project;
import com.hod.project.hive.entity.ProjectDetail;
import com.hod.project.hive.entity.ProjectManMonth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int addProject(@Param("project") ProjectDto project);
    Integer findLastInsertId();
    void addProjectUser(@Param("projectId") int projectId, @Param("userId") String userId, @Param("role") String role);
    void addProjectMm(@Param("projectId") int projectId, @Param("userId") String userId, @Param("projectYear") String projectYear, @Param("type") String type);
    List<Project> findProject(@Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("status") String status);
    float findProjectTotalMm(@Param("projectId") int projectId, @Param("type") String type, @Param("userId") String userId);
    ProjectDetail findProjectDetail(@Param("projectId") int projectId);
    void updateProject(@Param("project") ProjectDto project);
    void updateProjectUser(@Param("projectId") int projectId, @Param("userId") String userId, @Param("role") String role);
    List<ProjectManMonth> findProjectManMonth(@Param("projectYear") String projectYear, @Param("userId") String userId);
    int updateProjectManMonth(@Param("list") List<ProjectManMonth> list);
    List<ProjectDetail.ProjectUser> findUserList(@Param("projectId") int projectId);
    void deletePrject(@Param("projectId") int projectId);
    void deleteProjectUser(@Param("projectId") int projectId, @Param("userId") String userId);
    void deleteProjectMm(@Param("projectId") int projectId, @Param("userId") String userId);
}
