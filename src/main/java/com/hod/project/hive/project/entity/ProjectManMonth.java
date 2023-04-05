package com.hod.project.hive.project.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class ProjectManMonth {
    private String projectId;
    private String projectName;
    private String projectYear;
    private String type;
    private Date beginDate;
    private Date endDate;
    private String userId;
    private String userName;
    private Float m1;
    private Float m2;
    private Float m3;
    private Float m4;
    private Float m5;
    private Float m6;
    private Float m7;
    private Float m8;
    private Float m9;
    private Float m10;
    private Float m11;
    private Float m12;
    private String createdId;
    private Date createdDate;
    private String modifiedId;
    private Date modifiedDate;

    public List<Float> getMm() {
        return Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, this.totalManMonth());
    }

    public Float totalManMonth() {
        List<Float> list = Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12);
        return (float) list.stream()
                .filter(Objects::nonNull)
                .mapToDouble(mm -> mm)
                .sum();
    }
}
