package com.hod.project.hive.dto;

import com.hod.project.hive.common.dto.ManMonthDto;
import com.hod.project.hive.entity.ProjectManMonth;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ProjectManMonthRequest {
	private String projectId;
	private String userId;
	private String year;
	private List<ManMonthDto> mmList;

	public List<ProjectManMonth> toProjectManMonthList() {
		if (Objects.isNull(mmList) || mmList.isEmpty()) {
			throw new IllegalArgumentException("check mmList: " + mmList);
		}

		List<ProjectManMonth> result = new ArrayList<>();

		for (ManMonthDto mm : mmList) {
			ProjectManMonth item = new ProjectManMonth();
			item.setProjectId(projectId);
			item.setProjectYear(year);
			item.setUserId(userId);
			item.setType(mm.getType());
			item.setM1(mm.getMm().get(0));
			item.setM2(mm.getMm().get(1));
			item.setM3(mm.getMm().get(2));
			item.setM4(mm.getMm().get(3));
			item.setM5(mm.getMm().get(4));
			item.setM6(mm.getMm().get(5));
			item.setM7(mm.getMm().get(6));
			item.setM8(mm.getMm().get(7));
			item.setM9(mm.getMm().get(8));
			item.setM10(mm.getMm().get(9));
			item.setM11(mm.getMm().get(10));
			item.setM12(mm.getMm().get(11));
			item.setModifiedId(userId); // TODO: 요청자 아이디로 설정 필요

			result.add(item);
		}

		return result;
	}
}
