package com.hod.project.hive.project.service;

import com.hod.project.hive.project.entity.ProjectManMonth;
import com.hod.project.hive.project.dto.DashboardPersonalProjectDto;
import com.hod.project.hive.project.dto.ManMonthDto;
import com.hod.project.hive.project.dto.PersonalProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    /**
     * <pre>
     * 대시보드 > 프로젝트 > 개인 페이지 API
     * </pre>
     * @param projectYear 프로젝트 년도
     * @param userId 사용자 아이디
     * @return
     */
    @Transactional
    public DashboardPersonalProjectDto getDashboardProject(String projectYear, String userId) {
        List<ProjectManMonth> projectMmList = projectMapper.findProjectManMonth(projectYear, userId);

        Float expectTotalMm = getTotalMm(projectMmList, "EXPECT");
        Float actualTotalMm = getTotalMm(projectMmList, "ACTUAL");
        List<PersonalProjectDto> projectList = getProjectList(projectMmList);

        DashboardPersonalProjectDto result = new DashboardPersonalProjectDto();
        result.setYear(projectYear);
        result.setExpectTotalMm(expectTotalMm);
        result.setActualTotalMm(actualTotalMm);
        result.setProjectList(projectList);

        return result;
    }

    private List<PersonalProjectDto> getProjectList(List<ProjectManMonth> projectManMonthList) {
        Map<String, PersonalProjectDto> map = new HashMap<>();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (ProjectManMonth projectMm : projectManMonthList) {
            String key = projectMm.getProjectId() + "_" + projectMm.getUserId();
            PersonalProjectDto personalProjectDto = map.get(key);
            if (Objects.isNull(personalProjectDto)) {
                personalProjectDto = new PersonalProjectDto();
                personalProjectDto.setId(projectMm.getProjectId());
                personalProjectDto.setName(projectMm.getProjectName());
                personalProjectDto.setBeginDate(dateFormat.format(projectMm.getBeginDate()));
                personalProjectDto.setEndDate(dateFormat.format(projectMm.getEndDate()));
                personalProjectDto.setMmList(new ArrayList<>());

                map.put(key, personalProjectDto);
            }

            ManMonthDto mm = new ManMonthDto();
            mm.setType(projectMm.getType());
            mm.setMm(projectMm.getMm());

            personalProjectDto.getMmList().add(mm);
        }

        return map.values().stream().collect(Collectors.toList());
    }

    private Float getTotalMm(List<ProjectManMonth> list, String type) {
        return (float) list.stream()
                .filter(o -> type.equalsIgnoreCase(o.getType()))
                .mapToDouble(ProjectManMonth::totalManMonth)
                .sum();
    }

}
