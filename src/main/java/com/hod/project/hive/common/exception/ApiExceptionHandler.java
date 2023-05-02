package com.hod.project.hive.common.exception;

import com.hod.project.hive.common.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * <pre>
	 * 컨트롤러 오류 처리
	 * </pre>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Object> handleControllerException(ApiException e) {
		ApiResponse<Object> response = new ApiResponse<>();
		response.setResult(e.getResult());
		response.setReason(e.getMessage());
		return  ResponseEntity.ok(response);
	}

	/**
	 * <pre>
	 *     유효성 검사 에러 처리
	 * </pre>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
		List<Map<String, Object>> data = e.getConstraintViolations().stream()
				.map(v -> {
					Map<String, Object> result = new HashMap<>();
					result.put("value", v.getInvalidValue());
					result.put("reason", v.getMessage());
					return result;
				}).collect(Collectors.toList());

		ApiResponse<Object> response = new ApiResponse<>();
		response.setResult(ApiCode.INVALID_PARAMETER.getCode());
		response.setReason(ApiCode.INVALID_PARAMETER.getMessage());
		response.setData(data);

		return  ResponseEntity.ok(response);
	}

	/**
	 * <pre>
	 *     객체 유효성 검사 에러 처리
	 * </pre>
	 * @param e
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Map<String, Object>> data = e.getBindingResult().getAllErrors().stream()
				.map(v -> {
					Map<String, Object> result = new HashMap<>();
					result.put("message", v.getDefaultMessage());
					return result;
				}).collect(Collectors.toList());

		ApiResponse<Object> response = new ApiResponse<>();
		response.setResult(ApiCode.METHOD_ARGUMENT_NOT_VALID.getCode());
		response.setReason(ApiCode.METHOD_ARGUMENT_NOT_VALID.getMessage());
		response.setData(data);

		return ResponseEntity.ok(response);
	}

	/**
	 * <pre>
	 *     알 수 없는 에러 처리
	 * </pre>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception e) {
		ApiResponse<Object> response = new ApiResponse<>();
		response.setResult(ApiCode.UNKNOWN_ERROR.getCode());
		response.setReason(e.getMessage());
		return  ResponseEntity.ok(response);
	}

}
