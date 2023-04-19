package com.hod.project.hive.dto;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class PersonalProjectDto {

    private String projectId;
    private String projectName;
    private String userId;
    private Float progress;
    private String beginDate;
    private String endDate;
    private List<ManMonthDto> mmList;


    public Float getProgress() {
        Float progress = calculateProgress();
        return progress;
    }

    private Float calculateProgress() {
        if (Objects.isNull(mmList) || mmList.isEmpty()) {
            return null;
        }

        Float actualTotal = null;
        Float expectTotal = null;

        for (ManMonthDto dto : mmList) {
            String type = dto.getType();
            List<Float> mm = dto.getMm();

            switch (type) {
                case "ACTUAL":
                    actualTotal = mm.get(mm.size() - 1);
                    break;

                case "EXPECT":
                    expectTotal = mm.get(mm.size() - 1);
                    break;

                default:
                    continue;
            }
        }

        if (Objects.isNull(actualTotal) || Objects.isNull(expectTotal)) {
            return null;
        }

        float progressPercent = (actualTotal / expectTotal) * 100.0f;
        return Math.round(progressPercent * 100) / 100.0f;
    }

}
