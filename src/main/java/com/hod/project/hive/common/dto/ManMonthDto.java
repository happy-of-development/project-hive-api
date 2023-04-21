package com.hod.project.hive.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManMonthDto {
    private String type;
    private List<Float> mm;
}
