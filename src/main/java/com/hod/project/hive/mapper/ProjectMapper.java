package com.hod.project.hive.mapper;

import com.hod.project.hive.entity.Project;
import com.hod.project.hive.entity.ProjectDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<Project> findProject(@Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("status") String status);
    float findProjectTotalMm(@Param("id") String id, @Param("type") String type, @Param("userId") String userId);
    ProjectDetail findProjectDetail(@Param("id") String id);
    List<ProjectDetail.ProjectUser> findUserList(@Param("id") String id);

}
