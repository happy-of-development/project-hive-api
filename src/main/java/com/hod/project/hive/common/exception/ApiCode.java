package com.hod.project.hive.common.exception;

import lombok.*;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum ApiCode {
	SUCCESS("200", "성공"),
	FAIL("999", "실패"),
	BAD_REQUEST("400", "잘못된 요청 입니다."),
	USER_NOT_FOUND("404", "사용자 정보가 없습니다."),
	UNKNOWN_ERROR("500", "알 수 없는 오류입니다."),
	DB_UPDATE_FAIL("501", "서버 내부 오류", "DB 업데이트 실패"),
	INVALID_PARAMETER("502", "파라미터가 유효하지 않습니다."),
	METHOD_ARGUMENT_NOT_VALID("503", "객체가 유효하지 않습니다.")
	;

	@NonNull
	private String code;
	@NonNull
	private String message;

	private String data;
}
