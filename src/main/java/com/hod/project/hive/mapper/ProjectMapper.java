package com.hod.project.hive.mapper;

import com.hod.project.hive.entity.Project;
import com.hod.project.hive.entity.ProjectDetail;
import com.hod.project.hive.entity.ProjectManMonth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<Project> findProject(@Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("status") String status);
    float findProjectTotalMm(@Param("id") String id, @Param("type") String type);
    ProjectDetail findProjectDetail(@Param("id") String id);
    List<ProjectManMonth> findProjectManMonth(@Param("projectYear") String projectYear, @Param("userId") String userId);
    int updateProjectManMonth(@Param("list") List<ProjectManMonth> list);
}
