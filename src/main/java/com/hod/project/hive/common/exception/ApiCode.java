package com.hod.project.hive.common.exception;

import com.mysql.cj.protocol.Warning;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiCode {
	SUSSESS("200", "성공"),
	FAIL("999", "실패"),
	USER_NOT_FOUND("404", "사용자 정보가 없습니다."),
	UNKNOWN_ERROR("500", "알 수 없는 에러입니다."),
	DB_UPDATE_FAIL("501", "DB 업데이트 실패"),
	INVALID_PARAMETER("502", "파라미터가 유효하지 않습니다."),
	METHOD_ARGUMENT_NOT_VALID("503", "객체가 유효하지 않습니다.")
	;

	private String code;
	private String message;
}
