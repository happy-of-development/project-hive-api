package com.hod.project.hive.entity;

import lombok.Data;

@Data
public class Project {
    int id;
    String name;
    String pmId;
    String pmName;
    String beginDate;
    String endDate;
    String status;
    Float actualMm;
    Float expectMm;
}
