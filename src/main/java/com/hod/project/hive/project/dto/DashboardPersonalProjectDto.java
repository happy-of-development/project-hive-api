package com.hod.project.hive.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class DashboardPersonalProjectDto {

    private String year;
    private Float expectTotalMm;
    private Float actualTotalMm;
    private List<PersonalProjectDto> projectList;

}
