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
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Transactional
    public void addProject(ProjectDto project) {
        projectMapper.addProject(project);

        int projectId = projectMapper.findLastInsertId();

        List<String> userList = new ArrayList<>();
        for(ProjectDto.ProjectUser user : project.getUserList()) {
            userList.add(user.getId());
        }

        addProjectUserAndMm(projectId, project.getBeginDate(), project.getEndDate(), userList, project.getPmId());
    }

    public void addProjectUserAndMm(int projectId, String beginDate, String endDate, List<String> userList, String pmId) {
        int beginYear = LocalDate.parse(beginDate).getYear();
        int endYear = LocalDate.parse(endDate).getYear();

        for(String userId : userList) {
            projectMapper.addProjectUser(projectId, userId, userId.equals(pmId)?"PM":"");

            for(int i = beginYear; i <= endYear; i++) {
                projectMapper.addProjectMm(projectId, userId, String.valueOf(i), "ACTUAL");
                projectMapper.addProjectMm(projectId, userId, String.valueOf(i), "EXPECT");
            }
        }
    }

    public List<Project> getProject(String beginDate, String endDate, String status) {
        List<Project> projectList =  projectMapper.findProject(beginDate, endDate, status);
        for(Project project : projectList) {
           project.setExpectMm(projectMapper.findProjectTotalMm(project.getId(), "EXPECT", null));
           project.setActualMm(projectMapper.findProjectTotalMm(project.getId(), "ACTUAL", null));
        }

        return projectList;
    }

    public ProjectDetail getProjectDetail(int id) {
        ProjectDetail detail = projectMapper.findProjectDetail(id);
        detail.setExpectMm(projectMapper.findProjectTotalMm(id, "EXPECT", null));
        detail.setActualMm(projectMapper.findProjectTotalMm(id, "ACTUAL", null));

        List<ProjectDetail.ProjectUser> userList = projectMapper.findUserList(id);
        for(ProjectDetail.ProjectUser user : userList) {
            user.setActualMm(projectMapper.findProjectTotalMm(id, "ACTUAL", user.getId()));
        }

        detail.setUserList(userList);

        return detail;
    }

    public void updateProject(ProjectDto project) {
        projectMapper.updateProject(project);

        /* db에 있는 user와 새로들어온 user를 비교해 처리 */
        List<String> dbUserList = new ArrayList<>();
        List<String> newUserList = new ArrayList<>();
        List<String> addUserList = new ArrayList<>();

        for(ProjectDetail.ProjectUser user : projectMapper.findUserList(project.getId())) {
            dbUserList.add(user.getId());
        }

        for(ProjectDto.ProjectUser user : project.getUserList()) {
            newUserList.add(user.getId());
        }

        for(String newUser : newUserList) {
            if(dbUserList.contains(newUser)) {  //update TODO: modify 시간을 업데이트 하려면 PM이 바뀐거에만 적용해야함..
                projectMapper.updateProjectUser(project.getId(), newUser, newUser.equals(project.getPmId())?"PM":"");
                continue;
            }
            // insert 할 유저를 추가.
            addUserList.add(newUser);
        }

        // insert
        addProjectUserAndMm(project.getId(), project.getBeginDate(), project.getEndDate(), addUserList, project.getPmId());

        for(String dbUser : dbUserList) {
            if(!newUserList.contains(dbUser)) {
                projectMapper.deleteProjectMm(project.getId(), dbUser);
                projectMapper.deleteProjectUser(project.getId(), dbUser);
            }
        }

        // TODO : 프로젝트 시작, 종료 시간 변경에 의해 project mm 데이터를 지울 것인지 판단 필요. 남겨도 될 것 같긴함.
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

    @Transactional
    public void deleteProject(int id) {
        projectMapper.deleteProjectMm(id, null);
        projectMapper.deleteProjectUser(id, null);
        projectMapper.deletePrject(id);
    }
}
