package com.hod.project.hive.dto;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProjectRequest {
    @NotBlank(groups = {Update.class}, message = "프로젝트 id가 없습니다.")
    private int id;

    @NotBlank(message = "프로젝트 이름을 입력해주세요.")
    @Size(min = 1, max = 50, message = "프로젝트 이름은 1~50자리 입니다.")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "pm id 형식이 잘못 되었습니다.")
    private String pmId;

    @NotBlank(message = "프로젝트 시작 날짜를 입력해주세요.")
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "프로젝트 시작 날짜 형식이 잘못 되었습니다.")
    private String beginDate;

    @NotBlank(message = "프로젝트 종료 날짜를 입력해주세요.")
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "프로젝트 종료 날짜 형식이 잘못 되었습니다.")
    private String endDate;

    @Pattern(regexp="INIT|PROGRESS|END|ALL", message = "status 값이 잘못 입력 되었습니다.")
    private String status;

    private List<ProjectUser> userList;

    @Data
    public static class ProjectUser {
        @Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "프로젝트 구성원 id 형식이 잘못 되었습니다.")
        private String id;
    }

    @AssertTrue(message = "프로젝트 날짜가 잘못 입력되었습니다. 종료 날짜가 시작 날짜보다 앞 입니다.")
    public boolean isValid() {
        int result = beginDate.compareTo(endDate);

        return (result < 0);
    }

    public interface Update {
    }
}
