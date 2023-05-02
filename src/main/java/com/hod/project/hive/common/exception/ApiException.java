package com.hod.project.hive.common.exception;

public class ApiException extends RuntimeException {

	private ApiCode responseCode;

	public ApiException(ApiCode responseCode) {
		super(responseCode.getMessage());
		this.responseCode = responseCode;
	}

	public String getResult() {
		return responseCode.getCode();
	}
}
