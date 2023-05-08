package com.hod.project.hive.common.exception;

import org.springframework.util.StringUtils;

public class ApiException extends RuntimeException {

	private ApiCode responseCode;

	public ApiException(ApiCode responseCode, String message) {
		super(message);
		this.responseCode = responseCode;
	}

	public ApiException(ApiCode responseCode) {
		super(responseCode.getMessage());
		this.responseCode = responseCode;
	}

	public String getCode() {
		return responseCode.getCode();
	}

	public String getMessage() {
		return super.getMessage();
	}

	public Object getData() {
		return responseCode.getData();
	}
}
