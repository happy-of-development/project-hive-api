package com.hod.project.hive.entity;

import lombok.Data;

@Data
public class Project {
    private int id;
    private String name;
    private String pmId;
    private String pmName;
    private String beginDate;
    private String endDate;
    private String status;
    private Float actualMm;
    private Float expectMm;
}
