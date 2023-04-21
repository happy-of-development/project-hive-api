package com.hod.project.hive.dto;

import com.hod.project.hive.common.dto.ManMonthDto;
import lombok.Data;

import java.util.List;

@Data
public class ProjectManMonthResponse {
	private List<Project> projectList;


	@Data
	public static class Project {
		private String id;
		private String name;
		List<User> userList;
	}

	@Data
	public static class User {
		private String id;
		private String name;
		private List<ManMonthDto> mmList;
	}
}
