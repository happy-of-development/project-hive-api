package com.hod.project.hive.service;

import com.hod.project.hive.common.dto.ManMonthDto;
import com.hod.project.hive.dto.*;
import com.hod.project.hive.entity.Project;

import com.hod.project.hive.entity.ProjectDetail;
import com.hod.project.hive.entity.ProjectManMonth;
import com.hod.project.hive.mapper.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> getProject(String beginDate, String endDate, String status) {
        List<Project> projectList =  projectMapper.findProject(beginDate, endDate, status);
        for(Project project : projectList) {
           project.setExpectMm(Float.toString(projectMapper.findProjectTotalMm(project.getId(), "EXPECT")));
           project.setActualMm(Float.toString(projectMapper.findProjectTotalMm(project.getId(), "ACTUAL")));
        }

        return projectList;
    }

    public ProjectDetail getProjectDetail(String id) {
        ProjectDetail detail = projectMapper.findProjectDetail(id);
        return null;
    }

    /**
     * <pre>
     * 대시보드 > 프로젝트 > 개인 페이지 API
     * </pre>
     * @param projectYear 프로젝트 년도
     * @param userId 사용자 아이디
     * @return
     */
    @Transactional
    public DashboardProjectPersonalDto getDashboardProject(String projectYear, String userId) {
        List<ProjectManMonth> dao = projectMapper.findProjectManMonth(projectYear, userId);

        Float expectTotalMm = getTotalMm(dao, "EXPECT");
        Float actualTotalMm = getTotalMm(dao, "ACTUAL");
        List<DashboardProjectPersonalDto.Project> projectList = createDashboardProjectList(dao);

        DashboardProjectPersonalDto result = new DashboardProjectPersonalDto();
        result.setYear(projectYear);
        result.setExpectTotalMm(expectTotalMm);
        result.setActualTotalMm(actualTotalMm);
        result.setProjectList(projectList);
        return result;
    }

    private List<DashboardProjectPersonalDto.Project> createDashboardProjectList(List<ProjectManMonth> dao) {
        Map<String, DashboardProjectPersonalDto.Project> map = new HashMap<>();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (ProjectManMonth projectMm : dao) {
            String key = projectMm.getProjectId() + "_" + projectMm.getUserId();
            DashboardProjectPersonalDto.Project project = map.get(key);
            if (Objects.isNull(project)) {
                project = new DashboardProjectPersonalDto.Project();
                project.setProjectId(projectMm.getProjectId());
                project.setProjectName(projectMm.getProjectName());
                project.setUserId(projectMm.getUserId());
                project.setBeginDate(dateFormat.format(projectMm.getBeginDate()));
                project.setEndDate(dateFormat.format(projectMm.getEndDate()));
                project.setMmList(new ArrayList<>());

                map.put(key, project);
            }

            ManMonthDto mm = new ManMonthDto();
            mm.setType(projectMm.getType());
            mm.setMm(projectMm.getMm());

            project.getMmList().add(mm);
        }

        return map.values().stream().collect(Collectors.toList());
    }

    private Float getTotalMm(List<ProjectManMonth> list, String type) {
        Float total = (float) list.stream()
                .filter(o -> type.equalsIgnoreCase(o.getType()))
                .mapToDouble(ProjectManMonth::totalManMonth)
                .sum();

        return Math.round(total * 100) / 100.0f;
    }

    /**
     * <pre>
     *     프로젝트 MM 조회
     * </pre>
     * @param projectYear 프로젝트 년도
     * @return
     */
    public ProjectManMonthDto getProjectManMonth(String projectYear) {
        List<ProjectManMonth> dao = projectMapper.findProjectManMonth(projectYear, null);

        List<ProjectManMonthDto.Project> projectList = createProjectList(dao);

        ProjectManMonthDto result = new ProjectManMonthDto();
        result.setProjectList(projectList);
        return result;
    }

    private List<ProjectManMonthDto.Project> createProjectList(List<ProjectManMonth> dao) {
        Map<String, List<ProjectManMonth>> groupingByProjectId = dao.stream()
                .collect(Collectors.groupingBy(ProjectManMonth::getProjectId));

        Map<String, ProjectManMonthDto.Project> projectIdMap = new HashMap<>();

        groupingByProjectId.forEach((projectId, projectDto) -> {
            ProjectManMonthDto.Project project = projectIdMap.get(projectId);
            if (Objects.isNull(project)) {
                ProjectManMonth firstItem = projectDto.get(0);
                String projectName = firstItem.getProjectName();

                project = new ProjectManMonthDto.Project();
                project.setId(projectId);
                project.setName(projectName);
                project.setUserList(new ArrayList<>());

                projectIdMap.put(projectId, project);
            }

            Map<String, ProjectManMonthDto.User> userIdMap = new HashMap<>();
            for (ProjectManMonth projectMm : projectDto) {
                String userId = projectMm.getUserId();
                String userName = projectMm.getUserName();

                ProjectManMonthDto.User user = userIdMap.get(userId);
                if (Objects.isNull(user)) {
                    user = new ProjectManMonthDto.User();
                    user.setId(userId);
                    user.setName(userName);
                    user.setMmList(new ArrayList<>());

                    userIdMap.put(userId, user);
                }

                ManMonthDto mm = new ManMonthDto();
                mm.setType(projectMm.getType());
                mm.setMm(projectMm.getMm());

                user.getMmList().add(mm);
            }

            List<ProjectManMonthDto.User> userList = userIdMap.values().stream().collect(Collectors.toList());
            project.setUserList(userList);
        });

        List<ProjectManMonthDto.Project> result = projectIdMap.values().stream().collect(Collectors.toList());
        return result;
    }
}
