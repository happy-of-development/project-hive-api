package com.hod.project.hive.project.service;

import com.hod.project.hive.project.entity.ProjectManMonth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<ProjectManMonth> findProjectManMonth(@Param("projectYear") String projectYear, @Param("userId") String userId);
}
