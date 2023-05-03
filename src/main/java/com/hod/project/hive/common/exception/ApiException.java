package com.hod.project.hive.common.exception;

public class ApiException extends RuntimeException {

	private ApiCode responseCode;

	public ApiException(ApiCode responseCode) {
		super(responseCode.getMessage());
		this.responseCode = responseCode;
	}

	public String getCode() {
		return responseCode.getCode();
	}

	public String getMessage() {
		return responseCode.getMessage();
	}

	public Object getData() {
		return responseCode.getData();
	}
}
