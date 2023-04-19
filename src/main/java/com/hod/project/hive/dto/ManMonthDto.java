package com.hod.project.hive.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManMonthDto {
    private String type;
    private List<Float> mm;
}
