package com.hod.project.hive.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseParams {
	@AllArgsConstructor
	public enum Status {
		CODE_200("200"),
		;

		private String code;
	}


	public ResponseParams() {
	}

	public ResponseParams(Status status) {
		this.result = status.code;
	}

	private String result;
	private String reason;
}
